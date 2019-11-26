package com.huawei.insa2.comm.cngp.message;


import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;

/**
 * ���������¼���ص���Ϣ�ṹ��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPLoginRespMessage extends CNGPMessage{

  /**
   * ���巵�ص���Ϣ��ʽ
   * @param buf ���ص�����,��Ϣ��ˮ���Ժ����Ϣ��������ˮ��
   */
   public CNGPLoginRespMessage(byte buf[]) throws IllegalArgumentException{

       this.buf = new byte[33];
       if( buf.length != 33 ){//��Ϣ���Ȳ���ȷ,��ˮ��+��Ϣ��
           throw new IllegalArgumentException( CNGPConstant.SMC_MESSAGE_ERROR );
       }
       System.arraycopy(buf,0,this.buf,0,buf.length);
  }


  /**
   * ���ط������˵���֤��
   */
   public byte[] getAuthenticatorServer(){
       byte tmpbuf[] = new byte[16];
       System.arraycopy(this.buf,16,tmpbuf,0,16);
       return tmpbuf;
   }

  /**
   * ���ط�����֧�ֵ���߰汾��
   */
   public byte getVersion(){
       return this.buf[32];
   }

  /**
   * ������Ϣ�����е��ֶ�������
   */
   public String toString(){
       StringBuffer strBuf = new StringBuffer(300);
       strBuf.append("CNGPLoginRespMessage: ");
       strBuf.append("PacketLength=" + this.buf.length);
       strBuf.append(",RequestID=" + this.getRequestId());
       strBuf.append(",Status=" + this.getStatus()) ;
       strBuf.append(",SequenceId=" + this.getSequenceId());
       strBuf.append(",AuthenticatorServer="  + new String(this.getAuthenticatorServer()));
       strBuf.append(",Version=" + this.getVersion());
       return strBuf.toString();
   }

}
