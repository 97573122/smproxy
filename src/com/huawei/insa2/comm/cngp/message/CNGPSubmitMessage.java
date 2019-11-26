package com.huawei.insa2.comm.cngp.message;

import java.io.*;
import java.util.*;
import java.lang.IllegalArgumentException;
import java.util.Date;
import com.huawei.insa2.util.*;
import com.huawei.insa2.comm.cngp.CNGPConstant;
import java.text.SimpleDateFormat;

/**
 * ���巢�Ͷ�����Ϣ(CNGP_Submit)��
 * @author       linmeilong
 * @version      1.0
 */

public class CNGPSubmitMessage
    extends CNGPMessage {
  //��ŵ���ʱ�����Ϣ�ı���
  private StringBuffer strBuf;

  /**
   * ����һ�����Ͷ�����Ϣ,������Ϣ�ĸ��ֶε�ֵ�����Ը�����ֵ�����жϣ�������Ҫ�����׳��쳣
   * ��Ҫ�������Ĳ���ת��Ϊһ��byte���͵�����
   * @param SPId  SP����ҵ����
   * @param subType ����Ϣ������
   * @param needReport  �Ƿ񷵻�״̬����
   * @param priority �������ȼ�
   * @param serviceId ҵ������
   * @param feeType �ʷ�����
   * @param feeUserType �Ʒ��û������ֶ�
   * @param feeCode ÿ������Ϣ����Ϣ��
   * @param msgFormat ����Ϣ��ʽ
   * @param validTime ��Чʱ��
   * @param atTime ��ʱ����ʱ��
   * @param srcTermId ����Ϣ�����û�����
   * @param chargeTermId �Ʒ��û�����
   * @param destTermIdCount ����Ϣ���պ�������
   * @param destTermId ����Ϣ���պ���
   * @param msgLength ����Ϣ����
   * @param msgContent ����Ϣ����
   * @param protocolValue Э���ʶ
   * @throws IllegalArgumentException
   */
  public CNGPSubmitMessage(
      String SPId,//SP����ҵ����
      int subType,//����Ϣ�����ͣ�0��ȡ�����ģ�1�����Ļ�㲥����2���㲥�·���3�������·�������������
      int needReport,//�Ƿ񷵻�״̬���棨0����Ҫ��1��Ҫ��
      int priority,//�������ȼ�����0��3��3Ϊ��߼�
      String serviceId,//ҵ������
      String feeType,//�ʷ�����:00=��� 01=�����շ� 02=���� 03=�ⶥ 04=���¿۷����� 05=CR����  ����������
      int feeUserType,//�Ʒ��û������ֶ�,0����Ŀ���ն˼Ʒ� 1����Դ�ն˼Ʒ� 2����SP�Ʒ� 3�����ռƷ��û�����Ʒ� ����: ����
      String feeCode,//ÿ������Ϣ����Ϣ�ѣ���λ����
      int msgFormat,//����Ϣ��ʽ
      Date validTime,//��Чʱ��
      Date atTime,//��ʱ����ʱ��(null:��������)
      String srcTermId,//����Ϣ�����û�����
      String chargeTermId,//�Ʒ��û�����
      int destTermIdCount,//����Ϣ���պ�������
      String[] destTermId,//����Ϣ���պ���
      int msgLength,//����Ϣ����
      String msgContent,//����Ϣ����
      int protocolValue//Э���ʶ
      ) throws IllegalArgumentException {
    if (SPId == null) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":SPId " + CNGPConstant.STRING_NULL);
    }

    if (SPId.length() > 10) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":SPId " +
                                         CNGPConstant.STRING_LENGTH_GREAT +
                                         "10");
    }
    if (subType < 0 || subType > 3) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":subType " +
                                         CNGPConstant.INT_SCOPE_ERROR);
    }
    if (needReport < 0 || needReport > 1) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":needReport " +
                                         CNGPConstant.INT_SCOPE_ERROR);
    }

    if (priority < 0 || priority > 3) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":priority " +
                                         CNGPConstant.INT_SCOPE_ERROR);
    }
    if (serviceId == null) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":serviceId " +
                                         CNGPConstant.STRING_NULL);
    }

    if (serviceId.length() > 10) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":serviceId " +
                                         CNGPConstant.STRING_LENGTH_GREAT +
                                         "10");
    }
    if (feeType == null) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":feeType " +
                                         CNGPConstant.STRING_NULL);
    }

    if (feeType.length() > 2) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":feeType " +
                                         CNGPConstant.STRING_LENGTH_GREAT + "2");
    }

    if (feeUserType < 0 || feeUserType > 255) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":feeUserType " +
                                         CNGPConstant.INT_SCOPE_ERROR);
    }

    if (feeCode == null) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":feeCode " +
                                         CNGPConstant.STRING_NULL);
    }

    if (feeCode.length() > 6) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":feeCode " +
                                         CNGPConstant.STRING_LENGTH_GREAT + "6");
    }
    if (msgFormat < 0 || msgFormat > 255) {
      throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                         + ":msgFormat " +
                                         CNGPConstant.INT_SCOPE_ERROR);
    }
      if (srcTermId == null) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":srcTermId " +
                                           CNGPConstant.STRING_NULL);
      }

      if (srcTermId.length() > 21) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":srcTermId " +
                                           CNGPConstant.STRING_LENGTH_GREAT +
                                           "21");
      }
      if (chargeTermId == null) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":chargeTermId " +
                                           CNGPConstant.STRING_NULL);
      }

      if (chargeTermId.length() > 21) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":chargeTermId " +
                                           CNGPConstant.STRING_LENGTH_GREAT +
                                           "21");
      }
      if (destTermIdCount < 0 || destTermIdCount > 100) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":destTermIdCount " +
                                           CNGPConstant.INT_SCOPE_ERROR);
      }
      if (destTermId == null) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":destTermId " +
                                           CNGPConstant.STRING_NULL);
      }

      if (msgLength < 0 || msgLength > 255) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":msgLength " +
                                           CNGPConstant.INT_SCOPE_ERROR);
      }
      if (msgContent == null) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":msgContent " +
                                           CNGPConstant.STRING_NULL);
      }

      if (msgContent.length() > 254) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":msgContent " +
                                           CNGPConstant.STRING_LENGTH_GREAT +
                                           "254");
     }
      if (protocolValue < 0 || protocolValue > 255) {
        throw new IllegalArgumentException(CNGPConstant.SUBMIT_INPUT_ERROR
                                           + ":protocolValue " +
                                           CNGPConstant.INT_SCOPE_ERROR);
      }

      /************ת������Ĳ�����byte�������飬Ҳ���Ǳ���*******************/

      //�������Ϣ�ĳ��ȣ���Ϣͷ����Ϣ�壬Ϊ���������������ֶγ���(destTermId,msgContent)
      byte[] ms = null;
      try {
        ms = msgContent.getBytes("gb2312");
      }
      catch (UnsupportedEncodingException ex) {
        ex.printStackTrace();
      }

      int len = 132 + 21 * destTermIdCount + ms.length;

      this.buf = new byte[len]; //������Ϣ���ȿ��ٿռ�

      //�����Ϣͷ����
      setMsgLength(len);
      setRequestId(CNGPConstant.Submit_Request_Id);
      //8-11λΪ��Ϣ״̬����ʱ�����
      //12-15λΪ��ˮ�ţ�����ʱ��������ʱ�����

      //�����Ϣ�����
      System.arraycopy(SPId.getBytes(), 0, this.buf, 16, SPId.length());
      this.buf[26] = (byte) subType;
      this.buf[27] = (byte) needReport;
      this.buf[28] = (byte) priority;
      System.arraycopy(serviceId.getBytes(), 0, this.buf, 29, serviceId.length());
      System.arraycopy(feeType.getBytes(), 0, this.buf, 39, feeType.length());
      this.buf[41] = (byte)feeUserType;
      System.arraycopy(feeCode.getBytes(), 0, this.buf, 42, feeCode.length());
      this.buf[48] = (byte) msgFormat;

      SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");

      if (validTime != null) {
        String tmpTime = dateFormat.format(validTime) + "032+";
        System.arraycopy(tmpTime.getBytes(), 0, this.buf, 49, 16);
      }
      if (atTime != null) {
        String tmpTime = dateFormat.format(atTime) + "032+";
        System.arraycopy(tmpTime.getBytes(), 0, this.buf, 66, 16);
      }
      System.arraycopy(srcTermId.getBytes(), 0, this.buf, 83, srcTermId.length());
      System.arraycopy(chargeTermId.getBytes(), 0, this.buf, 104,
                       chargeTermId.length());
      this.buf[125] = (byte) destTermIdCount;
      int i = 0;
      for (i = 0; i < destTermIdCount; i++) {
        System.arraycopy(destTermId[i].getBytes(), 0, this.buf, 126 + i * 21,
                         destTermId[i].length());
      }
      int pos = 126 + 21 * destTermIdCount;
      this.buf[pos] = (byte) ms.length;
      pos++;
      //����Ϣ����
      System.arraycopy(ms, 0, this.buf, pos,
                       ms.length);
      pos += ms.length;

      //protocolTag
      TypeConvert.short2byte(0x100, this.buf, pos);
      pos += 2;

      TypeConvert.short2byte((short)1, this.buf, pos);
      pos += 2;

      this.buf[pos] = (byte) protocolValue;

      //������Ĳ�����¼��strBuf�����У��Ա��������Ļ
      strBuf = new StringBuffer(600);
      strBuf.append(",SPId=" + SPId);
      strBuf.append(",subType=" + subType);
      strBuf.append(",NeedReport=" + needReport);
      strBuf.append(",Priority=" + priority);
      strBuf.append(",ServiceID=" + serviceId);
      strBuf.append(",FeeType=" + feeType);
      strBuf.append(",feeUserType=" + feeUserType);
      strBuf.append(",FeeCode=" + feeCode);
      strBuf.append(",msgFormat=" + msgFormat);
      strBuf.append(",validTime=" + validTime);
      strBuf.append(",atTime=" + atTime);
      strBuf.append(",SrcTermID=" + srcTermId);
      strBuf.append(",ChargeTermID=" + chargeTermId);
      strBuf.append(",DestTermIDCount=" + destTermIdCount);
      for (int t = 0; t < destTermIdCount; t++) {
        strBuf.append(",DestTermID[" + t + "]=" + destTermId[t]);
      }
      strBuf.append(",MsgLength=" + ms.length);
      strBuf.append(",MsgContent=" + msgContent);
      strBuf.append(",protocolValue=" + protocolValue);
    }

    /**
     * ������Ϣ�����е��ֶ�������
     */
    public String toString() {

      StringBuffer outBuf = new StringBuffer(600);
      outBuf.append("CNGPSubmitMessage: ");
      outBuf.append("PacketLength=" + this.getMsgLength());
      outBuf.append(",RequestID=" + this.getRequestId());
      outBuf.append(",Status=" + this.getStatus());
      outBuf.append(",SequenceID=" + this.getSequenceId());
      if (strBuf != null) {
        outBuf.append(strBuf.toString());
      }
      return outBuf.toString();
    }

  }
