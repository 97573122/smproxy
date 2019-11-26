package com.huawei.insa2.comm.cngp;

import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cngp.message.*;
import com.huawei.insa2.util.Debug;
/**
 * PCNGP�����Զ�����
 * @author linmeilong
 * @version 1.0
 */
/// PLayer
public class CNGPTransaction extends PLayer {

  /** �յ���MSC��������Ϣ��*/
  private CNGPMessage receive;

  private int sequenceId;

  /**
   * ������������Ӷ��󴴽���
   * @param conn �������������ӡ�
   */
   public CNGPTransaction(PLayer connection) {
       super(connection);
       this.sequenceId=this.id;
   }

  /**
   * �յ��²��ϱ�����Ϣ������������֪ͨ���еȴ���Ϣ���̡߳����Ǹ������Ϣ�ַ�ʵ�֡�
   * @param msg ��Ӧ��Ϣ��
   */
   public synchronized void onReceive(PMessage msg) {
       receive = (CNGPMessage)msg;
       this.sequenceId = receive.getSequenceId();

       if( CNGPConstant.debug == true )
       {//�������ڵ���״̬������յ�����Ϣ��ӡ����Ļ
           Debug.dump(receive.toString());
       }
       //֪ͨ����������waitResponse()���߳�
       notifyAll();
   }

  /**
   * ����һ����Ϣ��
   * @param message ��������Ϣ��
   */
   public void send(PMessage message) throws PException {

       //����������Ϣ����ˮ�ţ�Ȼ�󽻸����Ӳ�
       CNGPMessage msg = (CNGPMessage)message;
       msg.setSequenceId(this.sequenceId);
       parent.send(message); //parent�Ǵ�PLayer�̳ж����ĳ�Ա����������Э��㣬�����Ӳ�
       if( CNGPConstant.debug == true )
       {//�������ڵ���״̬����ѷ��͵�����Ϣ��ӡ����Ļ
           Debug.dump(msg.toString());
       }
   }

  /**
   * ȡ�ø��������Ӧ���ݡ�
   * @return ��Ӧ��Ϣ����
   */
   public CNGPMessage getResponse() {
       return receive;
   }

  /**
   * �ȴ�һ����Ӧ���
   */
   public synchronized void waitResponse() {
       if (receive==null) {
           try {
               wait(((CNGPConnection)this.parent).getTransactionTimeout());
           }
           catch (InterruptedException ex) {}
       }
   }

}
