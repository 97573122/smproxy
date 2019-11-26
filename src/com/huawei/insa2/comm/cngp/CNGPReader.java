package com.huawei.insa2.comm.cngp;

import java.io.*;
//import java.util.*;
//import java.security.*;
//import java.lang.reflect.*;
import com.huawei.insa2.comm.*;
import com.huawei.insa2.comm.cngp.message.*;
import com.huawei.insa2.util.*;

/**
 * CNGPЭ�������������Ķ���ֻ�ڽ����߳��б�ִ�У���˲��ÿ��Ƕ��̱߳�����
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPReader extends PReader{

   /** �������Ϣ��������������*/
   protected DataInputStream in;

  /**
   * ����CNGPЭ���������
   */
   public CNGPReader(InputStream is) {

       in = new DataInputStream(is);
   }

  /**
   * �������������ݽ������Ϣ���������һ��������Ϣ�򷵻�null��
   * @param message ���������Ϣ����
   */
   public PMessage read() throws IOException {

       int totalLength = in.readInt();  //��Ϣ���ܳ���
       int commandId = in.readInt();    //��Ϣ������
       byte[] tmp = new byte[totalLength-8];
       in.readFully(tmp);

       //CNGP��Ϣ����Ϣͷ + ��Ϣ��
       byte[] buf = new byte[totalLength];
       TypeConvert.int2byte(totalLength,buf,0);
       TypeConvert.int2byte(commandId,buf,4);
       System.arraycopy(tmp,0,buf,8,totalLength-8);
       /*
       for(int k=0;k<totalLength;k++){
         System.out.print(buf[k]);
       }*/
       if( commandId == CNGPConstant.Login_Resp_Request_Id )
       {//��Ӧ����Ϊ��������Ӧ��
           return new CNGPLoginRespMessage(buf);
       }
       else if( commandId == CNGPConstant.Deliver_Request_Id )
       {//��Ϣ����Ϊ�����·�
           return new CNGPDeliverMessage(buf);
       }
       else if( commandId == CNGPConstant.Submit_Resp_Request_Id )
       {//��Ϣ����Ϊ�ύ����Ӧ��
           return new CNGPSubmitRespMessage(buf);
       }else if( commandId == CNGPConstant.Active_Test_Resp_Request_Id)
       {//��Ϣ����Ϊ��������Ӧ��
             return new CNGPActiveTestRespMessage(buf);
        }
        else{
           return null;
       }
   }
}
