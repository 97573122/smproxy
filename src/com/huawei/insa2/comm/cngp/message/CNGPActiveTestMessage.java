package com.huawei.insa2.comm.cngp.message;

import java.io.IOException;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;
/**
 * ���弤�������Ϣ��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPActiveTestMessage extends CNGPMessage{
   /**
    * ����һ���͸�SMC���������Ϣ����Ϣ��Ϊ��
    */
    public CNGPActiveTestMessage() {
        //�������Ϣ�ĳ��ȣ�
        int len = 16;
        this.buf = new byte[len]; //������Ϣ���ȿ��ٿռ�
        //�����Ϣͷ����
        setMsgLength(len);
        setRequestId(CNGPConstant.Active_Test_Request_Id);
        //��ˮ��8-11λΪ����״̬����ʱ�����
        //��ˮ��12-15λΪ��ˮ�ţ�����ʱ���
    }
   /**
    * ������Ϣ�����е��ֶ�������
    */
    public String toString(){
        StringBuffer strBuf = new StringBuffer(100);
        strBuf.append("CNGPActiveTestMessage: ");
        strBuf.append("PacketLength=" + this.getMsgLength());
        strBuf.append(",RequestID=" + this.getRequestId());
        strBuf.append(",Status=" + this.getStatus());
        strBuf.append(",SequenceId=" + this.getSequenceId());
        return strBuf.toString();
    }
}
