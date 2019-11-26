package com.huawei.insa2.comm.cngp.message;

/**
 * ���������¼��Ϣ�ṹ
 * @author linmeilong
 * @version 1.0
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.*;
import java.text.SimpleDateFormat;

public class CNGPLoginMessage extends CNGPMessage{

  //��ŵ���ʱ�����Ϣ�ı���
   private StringBuffer strBuf;

  /**
   * �����¼����Ϣ,����Ϣ�����жϣ�����Ҫ��ת��Ϊһ��byte���͵�����
   * @param clientId clientId
   * @loginMode   ��¼����
   * @param shared_Secret �ͻ�������
   * @param timeStamp ʱ���������
   * @param version ˫��Э�̵İ汾��
   */
  public CNGPLoginMessage(
     String clientId,
     String shared_Secret,
     int loginMode,
     Date timeStamp,
     int version
  ) throws IllegalArgumentException {

       /**������Ĳ������м��**/
       if( clientId == null ){
          throw new IllegalArgumentException(CNGPConstant.CONNECT_INPUT_ERROR
                                                   + ":clientId " + CNGPConstant.STRING_NULL);
       }
       if( clientId.length() > 10){
          throw new IllegalArgumentException(CNGPConstant.CONNECT_INPUT_ERROR
                                                   + ":clientId " + CNGPConstant.STRING_LENGTH_GREAT + "8");
       }
       if( shared_Secret.length() > 15){
          throw new IllegalArgumentException(CNGPConstant.CONNECT_INPUT_ERROR
                                                   + ":shared_Secret " + CNGPConstant.STRING_LENGTH_GREAT + "8");
       }

       if(loginMode <0 || loginMode >255){
            throw new IllegalArgumentException(CNGPConstant.CONNECT_INPUT_ERROR
                                                   + ":loginMode " + CNGPConstant.INT_SCOPE_ERROR);
       }

       if( version <0 || version > 255){
            throw new IllegalArgumentException(CNGPConstant.CONNECT_INPUT_ERROR
                                                   + ":version " + CNGPConstant.INT_SCOPE_ERROR);
       }

       //�������Ϣ�ĳ��ȣ���Ϣͷ����Ϣ��
       int len = 48;

       this.buf = new byte[len]; //������Ϣ���ȿ��ٿռ�

       //�����Ϣͷ����
       //������Ϣ���ܳ���
       setMsgLength(len);
       //������Ϣ�������ʶ
       setRequestId(CNGPConstant.Login_Request_Id);
       //8-11λΪ����״̬������ʱ����Ҫ���
       //12-15λΪ��Ϣ��ˮ�ţ�����ʱ��������ʱ����Ҫ���

       //�����Ϣ�����
       //ClientID,16-25λ
       System.arraycopy(clientId.getBytes(),0,this.buf,16,clientId.length());
       //SP��֤��26��41λ����MD5���ܵ� MD5(clientId + 7��0 + shared_Secret + 10λʱ��)
       if( shared_Secret != null ){
           len = clientId.length() + 17  + shared_Secret.length();
       }
       else{
          len = clientId.length() + 17;
       }
       byte[] tmpbuf = new byte[len];
       int tmploc = 0;
       System.arraycopy(clientId.getBytes(),0,tmpbuf,0,clientId.length());
       tmploc = clientId.length()+7;
       if( shared_Secret != null ){
           System.arraycopy(shared_Secret.getBytes(),0,tmpbuf,tmploc,shared_Secret.length());
           tmploc = tmploc + shared_Secret.length();
       }
       //��Date�͵�timeStampת��Ϊ10λ�ַ���
       SimpleDateFormat  dateFormat = new SimpleDateFormat("yyMMddHHmmss");
       String strTimeStamp = dateFormat.format(timeStamp).substring(2);
       System.arraycopy(strTimeStamp.toString().getBytes(),0,tmpbuf,tmploc,10);
       SecurityTools.md5(tmpbuf,0,len,this.buf,26);

       //��¼����
       this.buf[42] = (byte)loginMode;

       //43-46λ��ʱ��
       TypeConvert.int2byte(Integer.parseInt(strTimeStamp),buf,43);

       //�汾��
       this.buf[47] = (byte)version;

       //������Ĳ�����¼��outStr�����У��Ա��������Ļ
       strBuf = new StringBuffer(300);
       strBuf.append(",clientId=" + clientId);
       strBuf.append(",shared_Secret=" + shared_Secret);
       strBuf.append(",loginMode=" + loginMode);
       strBuf.append(",timeStamp=" + timeStamp);
       strBuf.append(",version=" + version);
    }

   /**
    * ������Ϣ�����е��ֶ�������
    */
    public String toString(){
        StringBuffer outStr = new StringBuffer(300);
        outStr.append("CNGPLoginMessage:");
        outStr.append("PacketLength=" + this.buf.length);
        outStr.append(",RequestID=" + CNGPConstant.Login_Request_Id);
        outStr.append(",SequenceId=" + this.getSequenceId());

        if(strBuf != null)
        {
           outStr.append(strBuf.toString());
        }
        return outStr.toString();
    }
}
