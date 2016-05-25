alter table t_user add open_id varchar2(50);
alter table t_user_account_detail add BUSINESS_NO varchar2(50);



-- Create table
create table T_SMS_SEND
(
  ID              NUMBER,
  SMS_ID          CHAR(32),
  TRANSTYPE       CHAR(3),
  SMS_TEMPLATE_ID VARCHAR2(10),
  SMS_CONTENT     VARCHAR2(500),
  MOBILE          VARCHAR2(200),
  SMS_APPLY       VARCHAR2(20),
  SMS_TIME        DATE,
  SMS_STATUS      VARCHAR2(2),
  RESULT_MSG      VARCHAR2(500),
  ISVALID         CHAR(1),
  CREATE_DATE     DATE,
  UPDATE_DATE     DATE,
  CHECKCODE       VARCHAR2(64)
);
alter table T_SMS_SEND add constraint PK_T_SMS_SEND primary key (SMS_ID);

create sequence SEQ_T_SMS_SEND minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_SMS_SEND BEFORE INSERT ON T_SMS_SEND FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_SMS_SEND.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

-- Add comments to the table 
comment on table T_SMS_SEND
  is '短信记录表';
-- Add comments to the columns 
comment on column T_SMS_SEND.ID
  is 'ID';
comment on column T_SMS_SEND.SMS_ID
  is 'SMS_ID';
comment on column T_SMS_SEND.TRANSTYPE
  is '短信类型';
comment on column T_SMS_SEND.SMS_TEMPLATE_ID
  is '短信模板ID';
comment on column T_SMS_SEND.SMS_CONTENT
  is '短信内容';
comment on column T_SMS_SEND.MOBILE
  is '发送手机号码';
comment on column T_SMS_SEND.SMS_APPLY
  is '发送短信通道';
comment on column T_SMS_SEND.ISVALID
  is '是否有效';
comment on column T_SMS_SEND.CHECKCODE
  is '验证码';
