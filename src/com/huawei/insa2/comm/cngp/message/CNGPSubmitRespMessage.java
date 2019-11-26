package com.huawei.insa2.comm.cngp.message;

import java.lang.IllegalArgumentException;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;

/**
 * ���巢�Ͷ�����Ӧ��Ϣ�ṹ��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPSubmitRespMessage extends CNGPMessage{

  /**
   * ����һ���Ͷ�����Ӧ��Ϣ
   * @param buf SMC���ص�����
   */
   public CNGPSubmitRespMessage(byte[] buf) throws IllegalArgumentException{

       this.buf = new byte[31];
       if( buf.length != 31 ){//��Ϣ���Ȳ���ȷ
           throw new IllegalArgumentException( CNGPConstant.SMC_MESSAGE_ERROR );
       }
       System.arraycopy(buf,0,this.buf,0,31);
   }

   /**
   * ������Ϣ��ʶ
   */
   public byte[] getMsgId(){
       byte[] tmpMsgId = new byte[10];
       System.arraycopy(this.buf,16,tmpMsgId,0,10);
       return tmpMsgId;
   }

   /**
    * ȡ��ӵ��״̬
    */
   public int getCongestionState(){
     return this.buf[30];
   }

  /**
   * ������Ϣ�����е��ֶ�������
   */
   public String toString(){
       StringBuffer strBuf = new StringBuffer(200);
       strBuf.append("CNGPSubmitRespMessage: ");
       strBuf.append("PacketLength=" + this.getMsgLength());
       strBuf.append(",RequestID=" + this.getRequestId());
       strBuf.append(",Status=" + this.getStatus());
       strBuf.append(",SequenceId=" + this.getSequenceId());
       strBuf.append(",MsgId=" + new String(this.getMsgId()));
       strBuf.append(",CongestionState=" + this.getCongestionState());
       return strBuf.toString();
   }
}
