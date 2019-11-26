package com.huawei.insa2.comm.cngp;



import java.io.*;
import java.net.*;
import java.util.*;
import com.huawei.insa2.util.*;
import com.huawei.insa2.comm.cngp.message.*;
import com.huawei.insa2.comm.*;
//import  anymusic.cngp.util.convertTools;
//import  anymusic.cngp.util.LogUtil;
/**
 * PCNGPЭ������Ӳ㡣
 * @author       linmeilong
 * @version      1.0
 */
public class CNGPConnection extends PSocketConnection{

   private int degree = 0;   //������,��������������������Ϣû����Ӧ�ĸ���

   private int hbnoResponseOut = 3;  //��������������Ϣû����Ӧ�ﵽ��������Ҫ����

   private String clientid = null; //SP��Ż���SMGW���

   private int loginMode = 2;     //��¼����

   private int version;   //˫��Э�̵İ汾��

   private String shared_secret; //�����̶���ֵ����������SP��֤��

   //private  convertTools cts=null;//�ַ�����ת��
   /**
   * ����һ��CNGPЭ�����ӡ�
   * @param args ���Ӵ�������Ҫ�Ĳ�����
   */
   public CNGPConnection(Args args) {
       hbnoResponseOut = args.get("heartbeat-noresponseout",3);
       clientid = args.get("clientid","huawei");
       //loginMode = args.get("loginmode",0);
       version = args.get("version",1);
       loginMode=args.get("loginmode",2);
       shared_secret = args.get("shared-secret","");
       CNGPConstant.debug = args.get("debug",false);
       //cts=convertTools.getInstance();
       //��ʼ��ϵͳ�Ĵ�����ʾ����
       CNGPConstant.initConstant(this.getResource());
       init(args);
   }

  /**
   * ��������ã���ñ���������õ��ı�������дCNGPMessage��������󽫵�����out����ָ��������
   * д������Ϣ�������ݱ�����ֽ����ݡ�
   * @param out �������������
   * @return ��Ϣ��������
   */
   protected PWriter getWriter(OutputStream out) {
       return new CNGPWriter(out);
   }

  /**
   * ��������ã���ý����������ý�������read()���������´Ӳ���inָ����������ж�ȡ�ֽ�����
   * ��������ϳ�CNGPMessage���������Ϣ����
   * @param in ����������Դ��
   * @return ��Ϣ��������
   */
   protected PReader getReader(InputStream in) {
       return new CNGPReader(in);
   }

  /**
   * ���Ǹ���ķ���������Ϣ��ȡ�õ�������Ҫ�������Զ����š�
   * @param message �������ص���Ϣ��
   * @return �����Զ����š�
   */
   public int getChildId(PMessage message) {
       CNGPMessage mes = (CNGPMessage)message;
       int sequenceId = mes.getSequenceId();

       if( mes.getRequestId() == CNGPConstant.Deliver_Request_Id)
       {//������ϢΪSMC�����·�����Ϣ�򷵻�-1
           return -1;
       }
       return sequenceId;
   }

  /**
   * ���Ǹ���ķ���������һ������
   * @return �ڸ������ϴ���������
   */
   public PLayer createChild() {
       return new CNGPTransaction(this);
   }

  /**
   * ȡ������ʱʱ�䡣�ȴ�MSC��������Ӧ��Ϣ��ȴ�ʱ�䡣��λ���롣
   * @return ����ʱʱ�䣬��λ���롣
   */
   public int getTransactionTimeout() {
       return transactionTimeout;
   }

  /**
   * �ṩ�õ���Դ�ַ����ķ�����
   * @return �����й��ʻ��ַ�������Դ����
   */
   public Resource getResource(){
       try{
           return new Resource(getClass(),"resource");
       }
       catch(IOException e){
           e.printStackTrace();
           return null;
       }
   }

  /**
   * �ȴ����ӿ��á����ڽ������Ӳ��������߳��н��У���������ȷ�������Ѿ�������ɿɵ��ñ�
   * ������
   */
  public synchronized void waitAvailable() {
    try {
      if (this.getError()==NOT_INIT) {
        wait(this.transactionTimeout);
      }
    } catch(InterruptedException ex) {
      //����
    }
  }

    /**
     * ��ֹ���ӡ�����֮�����ӽ����ò����á�
     */
    public void close() {
      //������ֹ����������ջ���Ӧ
      try {
        CNGPExitMessage msg = new CNGPExitMessage();
        send(msg);
      }
      catch (PException ex) {
        //����
      }
      super.close();
     }

  /**
   * ִ�ж���������
   * ���Э��������������Ӧ�ģ���ȴ���Ӧ����������ʧ�ܼ����ﵽ�ۼ�ֵ10ʱ�׳�IOException�쳣
   * �����ӽ����Զ����½�����
   */
    protected void heartbeat() throws IOException {
       //����������Ϣ
       //LogUtil.info(cts.toISO("ϵͳ�����ط���������Ϣ........................"),CNGPConnection.class);
       CNGPTransaction t = (CNGPTransaction)this.createChild();
       CNGPActiveTestMessage hbmes = new CNGPActiveTestMessage();

       t.send(hbmes);
       t.waitResponse();
       CNGPActiveTestRespMessage rsp = (CNGPActiveTestRespMessage)t.getResponse();
       if( rsp == null ){ //û�н��յ������ķ�����Ϣ
           this.degree ++;
           //LogUtil.info(cts.toISO("������Ϣ��"+degree+"���޷��أ�......................."),CNGPConnection.class);
           if( this.degree == this.hbnoResponseOut ){
               this.degree = 0;
               //LogUtil.info(cts.toISO("������Ϣ��"+this.hbnoResponseOut +"���޷��أ���ʱϵͳ����������......................."),CNGPConnection.class);
               throw new IOException(CNGPConstant.HEARTBEAT_ABNORMITY); //�׳��쳣����Ҫ����.�ж������������������������û�з��أ����׳��쳣
          }
       }
       else{
           this.degree = 0;
           //LogUtil.info(cts.toISO("�ɹ���������������Ϣ��..........................."),CNGPConnection.class);
       }
       t.close();
  }

  /**
   * ���ظ����connect����,�������õĲ�����ʼ�����ӣ��������״ν���TCP���ӣ��������߼�����,
   * Ҳ�����������������߼����ӡ�
   */
   protected synchronized void connect(){

       super.connect();
       if (!available()) { //��������û���ɹ������أ��ȴ��´�����
         return;
       }

       //����һ����������Ϣ
       CNGPLoginMessage request = null;
       //���յ�������Ϣ
       CNGPLoginRespMessage rsp = null;

       try{
           request = new CNGPLoginMessage(clientid,shared_secret,loginMode,
                        new Date(),version);
       }
       catch(IllegalArgumentException e){
           //������Ϣ���ɹ����׳��쳣
           e.printStackTrace();
           this.close();
           this.setError(CNGPConstant.CONNECT_INPUT_ERROR);
       }
       //�������ӳɹ��󣬷����߼���������
       //����һ����
       CNGPTransaction t = (CNGPTransaction)this.createChild();
       try {
           t.send(request);
           PMessage m = in.read();
           onReceive(m);
       }
       catch(IOException e){
           e.printStackTrace();
           this.close();
           setError(CNGPConstant.LOGIN_ERROR + explain(e));
       }

       rsp = (CNGPLoginRespMessage)t.getResponse();
       if (rsp==null) {
            //û�н��յ���Ϣ����ʼ�����ɹ����׳��쳣
           this.close();
           this.setError(CNGPConstant.CONNECT_TIMEOUT);
       }
       t.close();

       //�Է��ص���Ϣ�����ж�
       if( rsp != null )
       {
           if(rsp.getStatus() !=0 )
           {
               this.close();
               //�߼����Ӳ��ɹ����׳��쳣
               this.setError("Fail to login,the status code id " + rsp.getStatus());
           }
       }
       this.notifyAll();
   }
}
