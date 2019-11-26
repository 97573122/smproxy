package com.huawei.insa2.comm.cngp.message;

import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;

/**
 * �����·�������Ӧ��Ϣ,���͸�SMC��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPDeliverRespMessage extends CNGPMessage{

  //��ŵ���ʱ�����Ϣ�ı���
   private StringBuffer strBuf;

  /**
   * �����·���Ϣ����Ӧ
   * @param msgId  ��Ϣ��ʶ
   * @param congestionState  ���
   */
   public CNGPDeliverRespMessage(
      byte[] msgId,
      int congestionState
   )  throws IllegalArgumentException{

       //����Ϣ�Ĳ��������ж�
       if( msgId.length > 10 ){
          throw new IllegalArgumentException(CNGPConstant.DELIVER_REPINPUT_ERROR + ":msg_Id" + CNGPConstant.STRING_LENGTH_GREAT + "10");
       }
       if (congestionState < 0 || congestionState > 255) {
         throw new IllegalArgumentException(CNGPConstant.DELIVER_REPINPUT_ERROR
                                            + ":congestionState " +
                                            CNGPConstant.INT_SCOPE_ERROR);
       }
         //�������Ϣ�ĳ��ȣ���Ϣͷ����Ϣ��

         int len = 31;
         this.buf = new byte[31]; //������Ϣ���ȿ��ٿռ�

         //�����Ϣͷ����
         setMsgLength(len);
         setRequestId(CNGPConstant.Deliver_Resp_Request_Id);
         //��ˮ��8-11λΪ����״̬����ʱ�����
         //��ˮ��12-15λΪ��ˮ�ţ�����ʱ���

         //�����Ϣ��
         //��Ϣ��ʶ
         System.arraycopy(msgId, 0, this.buf, 16, msgId.length);
         //ӵ��״̬
         this.buf[30] = (byte) congestionState;

         //������Ĳ�����¼��outStr�����У��Ա��������Ļ

         strBuf = new StringBuffer(100);
         strBuf.append(",MsgId=" + msgId);
         strBuf.append(",congestionState=" + congestionState);
   }


   /**
    * ������Ϣ�����е��ֶ�������
    */
    public String toString(){
        StringBuffer outStr = new StringBuffer(100);
        outStr.append("CNGPDeliverRespMessage:");
        strBuf.append("PacketLength=" + this.getMsgLength());
        strBuf.append(",RequestID=" + this.getRequestId());
        strBuf.append(",Status=" + this.getStatus());
        outStr.append(",SequenceId=" + this.getSequenceId());
        if(strBuf != null)
        {
           outStr.append(strBuf.toString());
        }
        return outStr.toString();
    }

}
