package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.PMessage;
import com.huawei.insa2.util.*;
/**
 * CNGP��Ϣ�ĸ���
 * @author linmeilong
 * @version 1.0
 */

public abstract class CNGPMessage extends PMessage implements Cloneable{


   /**��Ϣ��ŵ�byte��������**/
   //���ڷ��͵���Ϣ������е��ӶΣ�������Ϣͷ����Ϣ��
   //���ڽ��յ���Ϣ�����ˮ������Ϣ�����Ϣ
   protected byte[] buf;

   public CNGPMessage() {
   }

   /**
    *���Ʊ���Ϣ�ķ���
    *@return ���ظ�������һ������Ϣ����
    */
   public Object clone() {
     try {
       CNGPMessage m = (CNGPMessage) super.clone();
       m.buf = (byte[]) this.buf.clone();
       return m;
     } catch(CloneNotSupportedException ex) {
       ex.printStackTrace();
       return null;
     }
   }
   //������,����Ϣ������ת��Ϊ�ַ���,������ʵ��
   public abstract String toString();

   //��ȡ��Ϣ�ĳ���
   public int getMsgLength(){
     int msgLength = TypeConvert.byte2int(this.buf,0);
     return msgLength;
   }

   //������Ϣ�ĳ���
   public void setMsgLength(int msgLength){
     TypeConvert.int2byte(msgLength,buf,0);
   }


   //��ȡ��Ϣ��RequestId
   public int getRequestId(){
     int requestId = TypeConvert.byte2int(this.buf,4);
     return requestId;
   }

   //������Ϣ��RequestId
   public void setRequestId(int requestId){
     TypeConvert.int2byte(requestId,buf,4);
   }

   //��ȡ��Ϣ��״̬
   public int getStatus(){
     int status = TypeConvert.byte2int(this.buf,8);
     return status;
   }

   //������Ϣ��״̬
   public void setStatus(int status){
     TypeConvert.int2byte(status,buf,8);
   }

   /**
    * ��ȡ��ˮ��
    */
   public int getSequenceId()
   {
     int sequenceId = TypeConvert.byte2int(buf,12);
       return sequenceId;
   }

   /**
    * ����������ˮ����
    */
   public void setSequenceId(int sequenceId){
        TypeConvert.int2byte(sequenceId,buf,12);
   }

   /**
    * ��ȡbyte[] ���͵���Ϣ
    */
   public byte[] getBytes(){
       return buf;
   }
}
