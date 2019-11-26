package com.huawei.insa2.comm.cngp.message;

import java.io.IOException;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;
/**
 * �����˳��������Ϣ��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPExitMessage extends CNGPMessage{


   /**
    * ����һ���͸�SMC�˳�������Ϣ����Ϣ��Ϊ��
    */
    public CNGPExitMessage() {
        //�������Ϣ�ĳ��ȣ�
        int len = 16;
        this.buf = new byte[len]; //������Ϣ���ȿ��ٿռ�

        //�����Ϣͷ����
        setMsgLength(len);
        setRequestId(CNGPConstant.Exit_Request_Id);
    }

   /**
    * ����һSMC�������͹������˳�֪ͨ��Ϣ
    * @param buf SMC���͹���������
    */
    public CNGPExitMessage(byte[] buf) throws IllegalArgumentException{
      this.buf = new byte[16];
      if( buf.length != 16 ){//��ˮ��
          throw new IllegalArgumentException( CNGPConstant.SMC_MESSAGE_ERROR );
      }
      System.arraycopy(buf,0,this.buf,0,16);
   }

   /**
    * ������Ϣ�����е��ֶ�������
    */
   /**
    * ������Ϣ�����е��ֶ�������
    */
    public String toString(){
        StringBuffer strBuf = new StringBuffer(100);
        strBuf.append("CNGPExitMessage: ");
        strBuf.append("PacketLength=" + this.getMsgLength());
        strBuf.append(",RequestID=" + this.getRequestId());
        strBuf.append(",Status=" + this.getStatus());
        strBuf.append(",SequenceId=" + this.getSequenceId());
        return strBuf.toString();
    }

}
