package com.huawei.insa2.comm.cngp.message;



import java.io.IOException;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;
/**
 * ����һ���������Ӧ��Ϣ��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPActiveTestRespMessage extends CNGPMessage{

   /**
    * ����һ�����ظ�SMC�ļ�����Ӧ��Ϣ
    * @param buf SMC������������
    */
    public CNGPActiveTestRespMessage(byte[] buf) throws IllegalArgumentException{

        this.buf = new byte[16];
        if( buf.length != 16 ){//��ˮ��
            throw new IllegalArgumentException( CNGPConstant.SMC_MESSAGE_ERROR );
        }
        System.arraycopy(buf,0,this.buf,0,16);
    }

    /**
    * ������Ϣ�����е��ֶ�������
    */
    public String toString(){
        StringBuffer strBuf = new StringBuffer(100);
        strBuf.append("CNGPActiveTestRespMessage: ");
        strBuf.append("PacketLength=" + this.getMsgLength());
        strBuf.append(",RequestID=" + this.getRequestId());
        strBuf.append(",Status=" + this.getStatus());
        strBuf.append(",SequenceId=" + this.getSequenceId());
        return strBuf.toString();
    }
}
