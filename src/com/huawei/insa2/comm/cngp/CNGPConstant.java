package com.huawei.insa2.comm.cngp;

/**
 * ����ϵͳ���õ��ĳ���
 * @author linmeilong
 * @version 1.0
 */

import com.huawei.insa2.util.Resource;

public class CNGPConstant {

  /**���ϵͳ�Ƿ�Ϊ����״̬*/
  public static boolean debug;

  /**������-���ڵ�¼*/
  public static String LOGINING;

  /**������-��¼���̳���*/
  public static String LOGIN_ERROR;

  /**������-��Ϣ��������*/
  public static String SEND_ERROR;

  /**������-���Ӳ��ɹ�*/
  public static String CONNECT_TIMEOUT;

  /**������-������Ϣ�ṹ��*/
  public static String STRUCTURE_ERROR;

  /**������-�Ƿ�SP_ID*/
  public static String NONLICETSP_ID;

  /**������-SP��֤��*/
  public static String SP_ERROR;

  /**������-�汾̫��*/
  public static String VERSION_ERROR;

  /**������-��������*/
  public static String OTHER_ERROR;

  /**������-�����쳣(����������Σ�û�з���ֵ�򷵻ص�ֵΪ���ɹ�)*/
  public static String HEARTBEAT_ABNORMITY;

  /**������-���Ͷ�����Ϣ������������*/
  public static String SUBMIT_INPUT_ERROR;

  /**������-ת��������Ϣ������������*/
  public static String FORWARD_INPUT_ERROR;

  /**������-mt_route_update������Ϣ������������*/
  public static String MTROUTEUPDATE_INPUT_ERROR;

  /**������-mo_route_update������Ϣ������������*/
  public static String MOROUTEUPDATE_INPUT_ERROR;

  /**������-����������Ϣ������������*/
  public static String CONNECT_INPUT_ERROR;

  /**������-ɾ������������Ϣ������������*/
    public static String CANCEL_INPUT_ERROR;

  /**������-���Ͷ���״̬��ѯ���������������*/
  public static String QUERY_INPUT_ERROR;

  /**������-�·�������Ϣ������������*/
  public static String DELIVER_REPINPUT_ERROR;

  /**������-������Ӧ�����������Ϣ������������*/
  public static String ACTIVE_REPINPUT_ERROR;

  /**������-SMC���͵���Ϣ����*/
  public static String SMC_MESSAGE_ERROR;

  /**������-С��0�����255*/
  public static String INT_SCOPE_ERROR;

  /**������-�ַ����ĳ��ȴ���*/
  public static String STRING_LENGTH_GREAT;

  /**������-ֵΪ��*/
  public static String STRING_NULL;

  /**��������������0��1*/
  public static String VALUE_ERROR;

  /**������-fee_UserType��ֵС��0�����3*/
  public static String FEE_USERTYPE_ERROR;

  /**������-registered_Delivery��ֵС��0�����2*/
  public static String REGISTERED_DELIVERY_ERROR;

  /**������-pk_Total��ֵС��1�����255*/
  public static String PK_TOTAL_ERROR;

  /**������-pk_Number��ֵС��1�����255*/
  public static String PK_NUMBER_ERROR;

  /**������-NeedReport��ֵ��Ϊ0��1*/
  public static String NEEDREPORT_ERROR;

  /**������-Priority��ֵС��0�����9*/
  public static String PRIORITY_ERROR;

  /**������-����Ϣ���պ����������100*/
  public static String DESTTERMID_ERROR;


  //����Э�����õ�Command_ID��ֵ
  public static final int Login_Request_Id = 0x00000001;

  public static final int Login_Resp_Request_Id = 0x80000001;

  public static final int Submit_Request_Id = 0x00000002 ;

  public static final int Submit_Resp_Request_Id = 0x80000002;

  public static final int Deliver_Request_Id = 0x00000003;

  public static final int Deliver_Resp_Request_Id = 0x80000003;

  public static final int Active_Test_Request_Id = 0x00000004;

  public static final int Active_Test_Resp_Request_Id =0x80000004;

  public static final int Forward_Request_Id = 0x00000005;

  public static final int Forward_Resp_Request_Id = 0x80000005;

  public static final int Exit_Request_Id = 0x00000006;

  public static final int Exit_Resp_Request_Id = 0x80000006;

  public CNGPConstant() {
    }

  /**
   * ϵͳ��ʼ��ʱ���ã���ȡ������Դ�ַ�����
   */
   public static void initConstant(Resource resource){
     if( LOGINING == null ){
       LOGINING = resource.get("smproxy/logining");
       LOGIN_ERROR = resource.get("smproxy/login-error");
       SEND_ERROR = resource.get("smproxy/send-error");
       CONNECT_TIMEOUT = resource.get("smproxy/connect-timeout");
       STRUCTURE_ERROR = resource.get("smproxy/structure-error");
       NONLICETSP_ID = resource.get("smproxy/nonlicetsp-id");
       SP_ERROR = resource.get("smproxy/sp-error");
       VERSION_ERROR = resource.get("smproxy/version-error");
       OTHER_ERROR = resource.get("smproxy/other-error");
       HEARTBEAT_ABNORMITY = resource.get("smproxy/heartbeat-abnormity");
       SUBMIT_INPUT_ERROR = resource.get("smproxy/submit-input-error");
       CONNECT_INPUT_ERROR = resource.get("smproxy/connect-input-error");
       CANCEL_INPUT_ERROR = resource.get("smproxy/cancel-input-error");
       QUERY_INPUT_ERROR = resource.get("smproxy/query-input-error");
       DELIVER_REPINPUT_ERROR = resource.get("smproxy/deliver-repinput-error");
       ACTIVE_REPINPUT_ERROR = resource.get("smproxy/active-repinput-error");
       SMC_MESSAGE_ERROR = resource.get("smproxy/smc-message-error");
       INT_SCOPE_ERROR = resource.get("smproxy/int-scope-error");
       STRING_LENGTH_GREAT = resource.get("smproxy/string-length-great");
       STRING_NULL = resource.get("smproxy/string-null");
       VALUE_ERROR = resource.get("smproxy/value-error");
       FEE_USERTYPE_ERROR = resource.get("smproxy/fee-usertype-error");
       REGISTERED_DELIVERY_ERROR = resource.get("smproxy/registered-delivery-erro");
       PK_TOTAL_ERROR = resource.get("smproxy/pk-total-error");
       PK_NUMBER_ERROR = resource.get("smproxy/pk-number-error");
       FORWARD_INPUT_ERROR = resource.get("smproxy/forward_input_error");
       MTROUTEUPDATE_INPUT_ERROR = resource.get("smproxy/mtrouteupdate_input_error");
       MOROUTEUPDATE_INPUT_ERROR = resource.get("smproxy/morouteupdate_input_error");
       NEEDREPORT_ERROR = resource.get("smproxy/needreport_error");
       PRIORITY_ERROR = resource.get("smproxy/priority_error");
       DESTTERMID_ERROR = resource.get("smproxy/desttermid_error");
     }
  }
}
