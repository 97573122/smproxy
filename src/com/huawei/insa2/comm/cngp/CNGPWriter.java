package com.huawei.insa2.comm.cngp;

import java.io.*;
import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cngp.message.CNGPMessage;

public class CNGPWriter extends PWriter{

  /** �������Ϣ��������������*/
  protected OutputStream out;

  /**
   * ����CNGPЭ���������
   */
   public CNGPWriter(OutputStream out) {
       this.out = out;
   }

  /**
   * ��CNGP��Ϣ���벢д��������С�
   * @param message ���������Ϣ����
   */
   public void write(PMessage message) throws IOException {
     out.write(((CNGPMessage) message).getBytes());
   }

  /**
   * ����������Ϣ��
   */
   public void writeHeartbeat() throws IOException {
      // out.write(HEARTBEAT);
   }
}
