package com.huawei.insa2.comm.cngp.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import com.huawei.insa2.util.TypeConvert;
import java.util.Calendar;

/**
 * ����SMC�·��Ķ�����Ϣ,SMC�����·��ġ�
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPDeliverMessage extends CNGPMessage{

    private int deliverType;//�Ƿ�״̬����:0:����,1:��
  /**
   * ����һSMC�·�������Ϣ
   * @param buf  SMC�·��Ķ�����Ϣ����
   */
   public CNGPDeliverMessage( byte[] buf )throws IllegalArgumentException {
       deliverType = 0;
       deliverType = buf[26];
       //������Ϣ���ȣ��̶�����+���̶�����
       int len = 90 + ((int)buf[84] & 0xff);
       if( buf.length != len ){//��Ϣ���Ȳ���ȷ,��ˮ��+��Ϣ��
           throw new IllegalArgumentException( CNGPConstant.SMC_MESSAGE_ERROR );
       }
       this.buf = new byte[len];
       System.arraycopy(buf,0,this.buf,0,buf.length);
    }

  /**
   * ������Ϣ��ʶ
   */
   public byte[] getMsgId(){
       byte[] msgId = new byte[10];
       System.arraycopy(this.buf,16,msgId,0,10);
       return msgId;
   }

  /**
   * �����Ƿ�Ϊ״̬���棬0������,1����
   */
   public int getIsReport(){
       return (int)this.buf[26];
  }

  /**
   * ������Ϣ��ʽ
   */
  public int getMsgFormat(){
     return (int)this.buf[27];
  }

  /**
   * ���ض���Ϣ���յ�ʱ��,27-40
   * @return
   */
  public Date getRecvTime()
  {
     try
     {
       //ȡ���
       int tmpYear = TypeConvert.byte2int(this.buf,27);
       //ȡ�·�
       byte[] tmpbyte = new byte[2];
       System.arraycopy(this.buf,31,tmpbyte,0,2);
       String tmpstr = new String(tmpbyte);
       int tmpMonth = Integer.parseInt(tmpstr);
       //ȡ����
       System.arraycopy(this.buf,33,tmpbyte,0,2);
       tmpstr = new String(tmpbyte);
       int tmpDay = Integer.parseInt(tmpstr);
       //ȡСʱ
       System.arraycopy(this.buf,35,tmpbyte,0,2);
       tmpstr = new String(tmpbyte);
       int tmpHour = Integer.parseInt(tmpstr);
       //ȡ����
       System.arraycopy(this.buf,37,tmpbyte,0,2);
       tmpstr = new String(tmpbyte);
       int tmpMinute = Integer.parseInt(tmpstr);
       //ȡ��
       System.arraycopy(this.buf,39,tmpbyte,0,2);
       tmpstr = new String(tmpbyte);
       int tmpSecond = Integer.parseInt(tmpstr);
       //����ʱ��
       Calendar calendar = Calendar.getInstance();
       calendar.set(tmpYear,tmpMonth,tmpDay,tmpHour,tmpMinute,tmpSecond);

       return calendar.getTime();
     }
     catch(Exception e)
     {
        return null;
     }
  }


  /**
   * ���ض���Ϣ���ͺ���
   * @return
   */
  public String getSrcTermID()
  {
     byte[] srcTermId = new byte[21];
     System.arraycopy(this.buf,41,srcTermId,0,21);
     return new String(srcTermId).trim();
  }

  /**
   * ���ض���Ϣ�Ľ��պ���
   * @return
   */
  public String getDestTermID()
  {
    byte[] destTermId = new byte[21];
    System.arraycopy(this.buf,62,destTermId,0,21);
    return new String(destTermId).trim();
  }

  public int getMsgLength()
  {
    return ((int)this.buf[84] & 0xff);
  }

  /**
   * ������Ϣ������
   */
   public String getMsgContent()
   {
     int len = ((int)this.buf[84] & 0xff);
     byte[] content = new byte[len];
     System.arraycopy(this.buf,85,content,0,len);
     return new String(content).trim();

   }

   /**
    * ����ӵ��״̬
    */
    public int getCongestionState()
    {
      int pos = 89 + ((int)this.buf[84] & 0xff);
      return this.buf[pos];

    }

    /**
     * ����״̬����
     */
    public String getStat()
    {
        if(deliverType == 1)
        {
            byte tmpStat[] = new byte[7];
            System.arraycopy(super.buf, 166, tmpStat, 0, 7);
            return (new String(tmpStat)).trim();
        } else
        {
            return null;
        }
    }

    /**
     * ����״̬������,SMGW�ύ����Ϣ��SMSC��ʱ��
     */
    public Date getSubmitDate()
    {
        if(deliverType == 1)
        {
            byte tmpbyte[] = new byte[2];
            int i = 129;
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            String tmpstr = new String(tmpbyte);
            i += 2;
            int tmpYear = 2000 + Integer.parseInt(tmpstr);
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            i += 2;
            int tmpMonth = Integer.parseInt(tmpstr) - 1;
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            i += 2;
            int tmpDay = Integer.parseInt(tmpstr);
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            i += 2;
            int tmpHour = Integer.parseInt(tmpstr);
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            int tmpMinute = Integer.parseInt(tmpstr);
            Calendar calendar = Calendar.getInstance();
            calendar.set(tmpYear, tmpMonth, tmpDay, tmpHour, tmpMinute);
            return calendar.getTime();
        } else
        {
            return null;
        }
    }

    /**
     * ����״̬������,����Ϣ����״̬����ʱ��
     */
    public Date getDoneDate()
    {
        if(deliverType == 1)
        {
            byte tmpbyte[] = new byte[2];
            int i = 150;
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            String tmpstr = new String(tmpbyte);
            i += 2;
            int tmpYear = 2000 + Integer.parseInt(tmpstr);
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            i += 2;
            int tmpMonth = Integer.parseInt(tmpstr) - 1;
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            i += 2;
            int tmpDay = Integer.parseInt(tmpstr);
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            i += 2;
            int tmpHour = Integer.parseInt(tmpstr);
            System.arraycopy(super.buf, i, tmpbyte, 0, 2);
            tmpstr = new String(tmpbyte);
            int tmpMinute = Integer.parseInt(tmpstr);
            Calendar calendar = Calendar.getInstance();
            calendar.set(tmpYear, tmpMonth, tmpDay, tmpHour, tmpMinute);
            return calendar.getTime();
        } else
        {
            return null;
        }
    }

  /**
   * ������Ϣ�����е��ֶ�������
   */
   public String toString(){

       StringBuffer strBuf = new StringBuffer(600);
       strBuf.append("CNGPDeliverMessage: ");
       strBuf.append("PacketLength=" + this.getMsgLength());
       strBuf.append(",RequestID=" + this.getRequestId());
       strBuf.append(",Status=" + this.getStatus());
       strBuf.append(",Sequence_Id=" + this.getSequenceId());
       strBuf.append(",MsgID=" + new String(this.getMsgId()));
       strBuf.append(",IsReport=" + this.getIsReport());
       strBuf.append(",MsgFormat=" + this.getMsgFormat());
       strBuf.append(",RecvTime=" + this.getRecvTime());
       strBuf.append(",SrcTermID=" + this.getSrcTermID());
       strBuf.append(",DestTermID=" + this.getDestTermID());
       strBuf.append(",MsgLength=" + this.getMsgLength());
       strBuf.append(",MsgContent=" + new String(this.getMsgContent()));
       if(deliverType == 1)
       {
           strBuf.append("[Stat=").append(this.getStat());
           strBuf.append(",SubmitDate=").append(this.getSubmitDate());
           strBuf.append(",DoneDate=").append(this.getDoneDate()).append("]");
       }

       strBuf.append(",CongestionState=" + this.getCongestionState());
       return strBuf.toString();
   }

}
