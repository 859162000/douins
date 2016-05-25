create table T_REQUEST_TRANS 
(
   ID                   NUMBER                         not null,
   REQUEST_TRANS_ID     CHAR(32)                       null,
   TRANS_NO             VARCHAR2(32)                   null,
   TRANS_ID             VARCHAR2(32)                   null,
   TRANS_TYPE           VARCHAR2(3)                    null,
   TRANS_CHANNEL        VARCHAR2(2)                    null,
   TRANS_TIME           DATE                           null,
   REQUEST_TIME         DATE                           null,
   IP_ADDRESS           VARCHAR2(32)                   null,
   CLIENT_TYPE          VARCHAR2(20)                   null,
   RESPONSE_CODE        VARCHAR2(10)                   null,
   RESPONSE_MSG         VARCHAR2(200)                  null,
   CREATE_DATE          DATE                           null,
   UPDATE_DATE          DATE                           null
);

comment on column T_REQUEST_TRANS.TRANS_NO is 
'交易号';

comment on column T_REQUEST_TRANS.TRANS_ID is 
'流水号';

comment on column T_REQUEST_TRANS.TRANS_TYPE is 
'交易类型';

comment on column T_REQUEST_TRANS.TRANS_CHANNEL is 
'交易渠道';

comment on column T_REQUEST_TRANS.TRANS_TIME is 
'交易时间';

comment on column T_REQUEST_TRANS.REQUEST_TIME is 
'请求时间';

comment on column T_REQUEST_TRANS.IP_ADDRESS is 
'IP地址';

comment on column T_REQUEST_TRANS.CLIENT_TYPE is 
'客户端类型';
alter table T_REQUEST_TRANS add constraint PK_T_REQUEST_TRANS primary key (REQUEST_TRANS_ID);

create sequence SEQ_T_REQUEST_TRANS minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_REQUEST_TRANS BEFORE INSERT ON T_REQUEST_TRANS FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_REQUEST_TRANS.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table T_USER 
(
   ID                   NUMBER(20)                     not null,
   USER_ID              CHAR(32)                       not null,
   USER_TYPE            CHAR(1)                        null,
   USER_ACCOUNT         VARCHAR2(64)                   not null,
   ACCOUNT_TYPE         VARCHAR2(2)                    null,
   LOGIN_PASSWORD       VARCHAR2(64)                   not null,
   USER_EMAIL           VARCHAR2(32)                   null,
   USER_MOBILEPHONE     VARCHAR2(15)                   null,
   REGISTER_TYPE        VARCHAR2(2)                    null,
   REGISTER_TIME        DATE                           null,
   USER_NICKNAME        VARCHAR2(64)                   null,
   STATUS               VARCHAR2(2)                    null,
   LAST_LOGIN_TIME      DATE                           null,
   IP_ADDRESS           VARCHAR2(32)                   null,
   NOVICE               CHAR(1)                        null,
   USER_GRADE           VARCHAR2(2)                    null,
   PAY_PASSWORD         VARCHAR2(64)                   null,
   GESTURE_PASSWORD     VARCHAR2(64)                   null,
   USER_NAME            VARCHAR2(64)                   null,
   USER_SEX             CHAR(1)                        null,
   USER_BIRTHDAY        DATE                           null,
   CERTI_TYPE           VARCHAR(2)                     null,
   CERTI_CODE           VARCHAR2(32)                   null,
   FETCH_TYPE           VARCHAR2(2)                    null,
   FETCH_TIME           DATE                           null,
   isvalid              CHAR(1)                        null,
   CREATE_DATE          DATE                           null,
   UPDATE_DATE          DATE                           null,
   OPUSER               CHAR(32)                       null,
   PROVINCE             VARCHAR2(32)                   null,
   CITY                 VARCHAR2(32)                   null,
   DISTRICT             VARCHAR2(32)                   null,
   DETAILED_ADRESS      VARCHAR2(200)                  null,
   ZIPCODE              VARCHAR2(6)                    null,
   SWITCH_PAYPASSWORD   VARCHAR2(1)                    null,
   SWITCH_GESTUREPASSWORD VARCHAR2(1)                    null
);

comment on table T_USER is 
'用户';

comment on column T_USER.ID is 
'ID';

comment on column T_USER.USER_ID is 
'USER_ID';

comment on column T_USER.USER_TYPE is 
'个人/企业';

comment on column T_USER.USER_ACCOUNT is 
'账户';

comment on column T_USER.ACCOUNT_TYPE is 
'账户类型';

comment on column T_USER.LOGIN_PASSWORD is 
'登录密码';

comment on column T_USER.USER_EMAIL is 
'邮箱';

comment on column T_USER.USER_MOBILEPHONE is 
'手机';

comment on column T_USER.REGISTER_TYPE is 
'来源';

comment on column T_USER.REGISTER_TIME is 
'注册时间';

comment on column T_USER.USER_NICKNAME is 
'昵称';

comment on column T_USER.STATUS is 
'状态';

comment on column T_USER.LAST_LOGIN_TIME is 
'最后登录时间';

comment on column T_USER.IP_ADDRESS is 
'登录IP';

comment on column T_USER.NOVICE is 
'是否新用户';

comment on column T_USER.USER_GRADE is 
'用户等级';

comment on column T_USER.PAY_PASSWORD is 
'支付密码';

comment on column T_USER.GESTURE_PASSWORD is 
'手势密码';

comment on column T_USER.USER_NAME is 
'姓名';

comment on column T_USER.USER_SEX is 
'性别';

comment on column T_USER.USER_BIRTHDAY is 
'出生日期';

comment on column T_USER.CERTI_TYPE is 
'证件类型';

comment on column T_USER.CERTI_CODE is 
'证件号码';

comment on column T_USER.FETCH_TYPE is 
'取回密码方式';

comment on column T_USER.FETCH_TIME is 
'取回密码时间';

comment on column T_USER.isvalid is 
'有效状态';

comment on column T_USER.CREATE_DATE is 
'创建时间';

comment on column T_USER.UPDATE_DATE is 
'跟新时间';

comment on column T_USER.OPUSER is 
'操作人';

comment on column T_USER.PROVINCE is 
'省';

comment on column T_USER.CITY is 
'市';

comment on column T_USER.DISTRICT is 
'区';

comment on column T_USER.DETAILED_ADRESS is 
'详细地址';

comment on column T_USER.ZIPCODE is 
'邮编';

comment on column T_USER.SWITCH_PAYPASSWORD is 
'支付密码开关  1 已初始化，0未初始化';

comment on column T_USER.SWITCH_GESTUREPASSWORD is 
'手势密码开关  1 已初始化，0未初始化';
alter table T_USER add constraint PK_T_USER primary key (USER_ID);

create sequence SEQ_T_USER minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_USER BEFORE INSERT ON T_USER FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_USER.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table t_customer 
(
   ID                   number                         not null,
   CUSTOMER_ID          char(32)                       not null,
   USER_ID              char(32)                       null,
   NAME                 varchar2(64)                   null,
   CARD_TYPE            varchar2(2)                    null,
   CARD_NO              varchar2(32)                   null,
   SEX                  char(1)                        null,
   CARD_VALID_DATE      date                           null,
   BIRTHDAY             date                           null,
   EMAIL                varchar2(64)                   null,
   PHONE                varchar2(20)                   null,
   MOBILEPHONE          varchar2(20)                   null,
   POSTCODE             varchar2(10)                   null,
   ADDRESS              varchar2(200)                  null,
   LIVE_REGION          varchar2(16)                   null,
   JOB                  varchar2(10)                   null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_customer is 
'客户表';

comment on column t_customer.NAME is 
'姓名';

comment on column t_customer.CARD_TYPE is 
'证件类型';

comment on column t_customer.CARD_NO is 
'证件号码';

comment on column t_customer.SEX is 
'性别';

comment on column t_customer.CARD_VALID_DATE is 
'证件有效期';

comment on column t_customer.BIRTHDAY is 
'出生日期';

comment on column t_customer.EMAIL is 
'邮箱';

comment on column t_customer.PHONE is 
'电话';

comment on column t_customer.MOBILEPHONE is 
'手机';

comment on column t_customer.POSTCODE is 
'邮编';

comment on column t_customer.ADDRESS is 
'地址';

comment on column t_customer.LIVE_REGION is 
'居住地区';

comment on column t_customer.JOB is 
'工作类型';

alter table t_customer add constraint PK_t_customer primary key (CUSTOMER_ID);

create sequence SEQ_t_customer minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_customer BEFORE INSERT ON t_customer FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_customer.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table t_policy_master 
(
   ID                   number                         not null,
   POLICY_ID            char(32)                       not null,
   PRODUCT_ID           char(32)                       null,
   INSURANCE_ID         char(32)                       null,
   USER_ID              char(32)                       null,
   ORDER_NO             varchar2(32)                   null,
   PAY_ORDER_NO         varchar2(32)                   null,
   STATUS               varchar2(2)                    null,
   APPLY_TIME           date                           null,
   UNDERWRITIND_TIME    date                           null,
   PAY_TIME             date                           null,
   VALIDATE_DATE        date                           null,
   EXPIRE_DATE          date                           null,
   END_TIME             date                           null,
   APPLICANT_ID         char(32)                       null,
   INSURED_ID           char(32)                       null,
   APPLY_NUM            number(12)                     null,
   TOTAL_PREM           number(15,2)                   null,
   TOTAL_REVENUE        number(15,2)                   null,
   PREM                 number(15,2)                   null,
   REPAY_AMOUNT         number(15,2)                   null,
   SEND_CODE            varchar2(32)                   null,
   POLICY_CODE          varchar2(32)                   null,
   COMMENTS             varchar2(200)                  null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   POLICY_URL           varchar2(300)                  null,
   UPDATE_DATE          date                           null,
   RELATION             varchar2(2)                    null
);

comment on table t_policy_master is 
'保单表';

comment on column t_policy_master.ORDER_NO is 
'订单编码';

comment on column t_policy_master.PAY_ORDER_NO is 
'支付订单编码';

comment on column t_policy_master.STATUS is 
'状态';

comment on column t_policy_master.APPLY_TIME is 
'下单时间';

comment on column t_policy_master.UNDERWRITIND_TIME is 
'核保时间';

comment on column t_policy_master.PAY_TIME is 
'支付时间';

comment on column t_policy_master.VALIDATE_DATE is 
'生效时间';

comment on column t_policy_master.EXPIRE_DATE is 
'到期时间';

comment on column t_policy_master.END_TIME is 
'赎回时间';

comment on column t_policy_master.APPLICANT_ID is 
'投保人ID';

comment on column t_policy_master.INSURED_ID is 
'被保险人ID';

comment on column t_policy_master.APPLY_NUM is 
'购买份数';

comment on column t_policy_master.TOTAL_PREM is 
'总保费';

comment on column t_policy_master.TOTAL_REVENUE is 
'预期总收益';

comment on column t_policy_master.PREM is 
'单价';

comment on column t_policy_master.REPAY_AMOUNT is 
'预期赎回金额';

comment on column t_policy_master.SEND_CODE is 
'保险公司投保单号码';

comment on column t_policy_master.POLICY_CODE is 
'保险公司保单号码';

comment on column t_policy_master.POLICY_URL is 
'电子保单URL';

comment on column t_policy_master.RELATION is 
'投被保险人关系';

alter table t_policy_master add constraint PK_t_policy_master primary key (POLICY_ID);

create sequence SEQ_t_policy_master minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_policy_master BEFORE INSERT ON t_policy_master FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_policy_master.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table T_USER_PAYACCOUNT 
(
   ID                   NUMBER                         not null,
   ACCOUNT_ID           CHAR(32)                       null,
   USER_ID              CHAR(32)                       null,
   USER_ACCOUNT_ID      CHAR(32)                       null,
   ACCOUNT_TYPE         CHAR(1)                        null,
   ACCOUNT_NAME         VARCHAR2(100)                  null,
   ACCOUNT_NO           VARCHAR2(30)                   null,
   BANK_PROVINCE        VARCHAR2(50)                   null,
   BANK_CITY            VARCHAR2(50)                   null,
   BANK_BRANCH          VARCHAR2(64)                   null,
   BANK_CODE            VARCHAR2(10)                   null,
   CARDHOLDER_NAME      VARCHAR2(32)                   null,
   CARDHOLDER_CERTICODE VARCHAR2(32)                   null,
   CARDHOLDER_CERTITYPE VARCHAR2(2)                    null,
   BANKMOBILE           VARCHAR2(15)                   null,
   STATUS               VARCHAR2(2)                    null,
   OPUSER               VARCHAR2(64)                   null,
   ISVALID              CHAR(1)                        null,
   CREATE_DATE          DATE                           null,
   UPDATE_DATE          DATE                           null
);

comment on table T_USER_PAYACCOUNT is 
'客户支付账号表';

comment on column T_USER_PAYACCOUNT.ID is 
'ID';

comment on column T_USER_PAYACCOUNT.ACCOUNT_ID is 
'支付账号ID';

comment on column T_USER_PAYACCOUNT.USER_ID is 
'客户ID';

comment on column T_USER_PAYACCOUNT.ACCOUNT_TYPE is 
'账号类型 1银行卡，2微信，3支付宝';

comment on column T_USER_PAYACCOUNT.ACCOUNT_NAME is 
'账户名称（银行名称）、支付宝、微信';

comment on column T_USER_PAYACCOUNT.ACCOUNT_NO is 
'账户号（银行卡号）、支付宝账号、微信账号';

comment on column T_USER_PAYACCOUNT.BANK_PROVINCE is 
'开户行所在省';

comment on column T_USER_PAYACCOUNT.BANK_CITY is 
'开户行所在市';

comment on column T_USER_PAYACCOUNT.BANK_BRANCH is 
'分行名称';

comment on column T_USER_PAYACCOUNT.BANK_CODE is 
'银行编码';

comment on column T_USER_PAYACCOUNT.CARDHOLDER_NAME is 
'持卡人姓名';

comment on column T_USER_PAYACCOUNT.CARDHOLDER_CERTICODE is 
'持卡人身份证';

comment on column T_USER_PAYACCOUNT.CARDHOLDER_CERTITYPE is 
'证件类型';

comment on column T_USER_PAYACCOUNT.BANKMOBILE is 
'银行预留手机';

comment on column T_USER_PAYACCOUNT.OPUSER is 
'操作人';

comment on column T_USER_PAYACCOUNT.ISVALID is 
'是否有效';

comment on column T_USER_PAYACCOUNT.CREATE_DATE is 
'创建时间';

comment on column T_USER_PAYACCOUNT.UPDATE_DATE is 
'更新时间';

alter table T_USER_PAYACCOUNT add constraint PK_T_USER_PAYACCOUNT primary key (ACCOUNT_ID);

create sequence SEQ_T_USER_PAYACCOUNT minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_USER_PAYACCOUNT BEFORE INSERT ON T_USER_PAYACCOUNT FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_USER_PAYACCOUNT.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table T_USER_ACCOUNT 
(
   ID                   NUMBER(20)                     not null,
   USER_ACCOUNT_ID      CHAR(32)                       null,
   USER_ID              CHAR(32)                       null,
   ACCOUNT_AMOUNT       NUMBER(24,8)                   null,
   POINT                NUMBER(16,2)                   null,
   ACCOUNT_BALANCE      NUMBER(24,8)                   null,
   POLICY_VALUE         NUMBER(24,8)                    null,
   LOAN_AMOUNT          NUMBER(24,8)                   null,
   VIRTUAL_ACCOUNT_NO   VARCHAR2(20)                   null,
   SURR_AMOUNT          NUMBER(24,8)                   null,
   REPAY_AMOUNT         NUMBER(24,8)                   null,
   STATUS               VARCHAR2(2)                    null,
   ISVALID              CHAR(1)                        null,
   CREATE_DATE          DATE                           null,
   UPDATE_DATE          DATE                           null
);

comment on table T_USER_ACCOUNT is 
'用户虚拟账户表';

comment on column T_USER_ACCOUNT.ACCOUNT_AMOUNT is 
'账户总价值';

comment on column T_USER_ACCOUNT.POINT is 
'积分值';

comment on column T_USER_ACCOUNT.ACCOUNT_BALANCE is 
'账户现金余额';

comment on column T_USER_ACCOUNT.POLICY_VALUE is 
'保单价值';

comment on column T_USER_ACCOUNT.LOAN_AMOUNT is 
'借款金额';

comment on column T_USER_ACCOUNT.VIRTUAL_ACCOUNT_NO is 
'虚拟账户号码';

comment on column T_USER_ACCOUNT.SURR_AMOUNT is 
'退保金额';

comment on column T_USER_ACCOUNT.REPAY_AMOUNT is 
'还款金额';


alter table T_USER_ACCOUNT add constraint PK_T_USER_ACCOUNT primary key (USER_ACCOUNT_ID);

create sequence SEQ_T_USER_ACCOUNT minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_USER_ACCOUNT BEFORE INSERT ON T_USER_ACCOUNT FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_USER_ACCOUNT.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table t_user_account_detail 
(
   ID                   number                         not null,
   USER_ACCOUNT_DETAIL_ID char(32)                       not null,
   USER_ACCOUNT_ID      char(32)                       null,
   BUSINESS_ID          char(32)                       null,
   DETAIL_TYPE          varchar2(4)                    null,
   DETAIL_IO            varchar2(2)                    null,
   FROM_ACCOUNT_NAME    varchar2(64)                   null,
   FROM_ACCOUNT_NO      varchar2(32)                   null,
   FROM_BANK_CODE       varchar2(32)                   null,
   FROM_BANK_NAME       varchar2(64)                   null,
   TO_ACCOUNT_NAME      varchar2(64)                   null,
   TO_ACCOUNT_NO        varchar2(32)                   null,
   TO_BANK_CODE         varchar2(32)                   null,
   TO_BANK_NAME         varchar2(64)                   null,
   APPLY_TIME           date                           null,
   END_TIME             date                           null,
   APPLY_AMOUNT         number(15,2)                   null,
   STATUS               varchar2(2)                    null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_user_account_detail is 
'虚拟账户明细';

comment on column t_user_account_detail.DETAIL_TYPE is 
'明细类型1-充值 2-提现 3-购买保单 4-保单借款打款  5-保单还款';

comment on column t_user_account_detail.DETAIL_IO is 
'进出类型1-入账 -1-出账';

comment on column t_user_account_detail.FROM_ACCOUNT_NAME is 
'账户名';

comment on column t_user_account_detail.FROM_ACCOUNT_NO is 
'账户号码';

comment on column t_user_account_detail.FROM_BANK_CODE is 
'银行编码';

comment on column t_user_account_detail.FROM_BANK_NAME is 
'银行名称';

comment on column t_user_account_detail.APPLY_TIME is 
'申请时间';

comment on column t_user_account_detail.END_TIME is 
'完成时间';

comment on column t_user_account_detail.APPLY_AMOUNT is 
'金额';

alter table t_user_account_detail add constraint PK_t_user_account_detail primary key (USER_ACCOUNT_DETAIL_ID);

create sequence SEQ_t_user_account_detail minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_user_account_detail BEFORE INSERT ON t_user_account_detail FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_user_account_detail.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table T_USER_ACCOUNT_OPEN_APPLY 
(
   ID                   NUMBER(20)                     not null,
   OPEN_APPLY_ID        CHAR(32)                       null,
   USER_ID              CHAR(32)                       null,
   ACCOUNT_ID           CHAR(32)                       null,
   STATUS               VARCHAR2(2)                    null,
   VIRTUAL_ACCOUNT_NO   VARCHAR2(20)                   null,
   ISVALID              CHAR(1)                        null,
   CREATE_DATE          DATE                           null,
   UPDATE_DATE          DATE                           null
);

comment on table T_USER_ACCOUNT_OPEN_APPLY is 
'客户开发申请表';

comment on column T_USER_ACCOUNT_OPEN_APPLY.STATUS is 
'状态';

comment on column T_USER_ACCOUNT_OPEN_APPLY.VIRTUAL_ACCOUNT_NO is 
'虚拟账户号码';

alter table T_USER_ACCOUNT_OPEN_APPLY add constraint PK_T_USER_ACCOUNT_OPEN_APPLY primary key (OPEN_APPLY_ID);

create sequence SEQ_T_USER_ACCOUNT_OPEN_APPLY minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_USER_ACCOUNT_OPEN_APPLY BEFORE INSERT ON T_USER_ACCOUNT_OPEN_APPLY FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_USER_ACCOUNT_OPEN_APPLY.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;



create table t_payment_apply 
(
   ID                   number                         not null,
   PAYMENT_APPLY_ID     char(32)                       not null,
   POLICY_ID            char(32)                       null,
   USER_ACCOUNT_ID      char(32)                       null,
   PAY_ORDER_NO         varchar2(32)                   null,
   PAY_TYPE             varchar2(2)                    null,
   THIRDPAY_TYPE        varchar2(2)                    null,
   FROM_ACCOUNT_NAME    varchar2(64)                   null,
   FROM_ACCOUNT_NO      varchar2(32)                   null,
   FROM_BANK_CODE       varchar2(32)                   null,
   FROM_BANK_NAME       varchar2(64)                   null,
   TO_ACCOUNT_NAME      varchar2(64)                   null,
   TO_ACCOUNT_NO        varchar2(32)                   null,
   TO_BANK_CODE         varchar2(32)                   null,
   TO_BANK_NAME         varchar2(64)                   null,
   PAY_APPLY_TIME       date                           null,
   PAY_TIME             date                           null,
   PAY_MONEY            number(15,2)                   null,
   STATUS               varchar2(2)                    null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);


comment on table t_payment_apply is 
'支付信息';

comment on column t_payment_apply.USER_ACCOUNT_ID is 
'虚拟账户ID';

comment on column t_payment_apply.PAY_ORDER_NO is 
'支付编码';

comment on column t_payment_apply.THIRDPAY_TYPE is 
'第三方支付';

comment on column t_payment_apply.FROM_ACCOUNT_NAME is 
'账户名';

comment on column t_payment_apply.FROM_ACCOUNT_NO is 
'账户号码';

comment on column t_payment_apply.PAY_APPLY_TIME is 
'申请支付时间';

comment on column t_payment_apply.PAY_TIME is 
'支付完成时间';

comment on column t_payment_apply.PAY_MONEY is 
'支付金额';

alter table t_payment_apply add constraint PK_t_payment_apply primary key (PAYMENT_APPLY_ID);

create sequence SEQ_t_payment_apply minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_payment_apply BEFORE INSERT ON t_payment_apply FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_payment_apply.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table t_apply_info 
(
   ID                   number                         not null,
   APPLY_INFO_ID        char(32)                       not null,
   POLICY_ID            char(32)                       null,
   APPLY_TYPE           varchar2(32)                   null,
   APPLY_TIME           date                           null,
   APPLY_CUSTOMER_ID    char(32)                       null,
   VALIDATE_TIME        date                           null,
   STATUS               varchar2(2)                    null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_apply_info is 
'申请汇总表';

comment on column t_apply_info.APPLY_TYPE is 
'申请类型SU-退保 LO-借款 RE-还款';

comment on column t_apply_info.APPLY_TIME is 
'申请时间';

comment on column t_apply_info.APPLY_CUSTOMER_ID is 
'申请人';

comment on column t_apply_info.VALIDATE_TIME is 
'生效时间';

alter table t_apply_info add constraint PK_t_apply_info primary key (APPLY_INFO_ID);

create sequence SEQ_t_apply_info minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_apply_info BEFORE INSERT ON t_apply_info FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_apply_info.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table t_surrend_apply 
(
   ID                   number                         not null,
   SURREND_APPLY_ID     char(32)                       not null,
   APPLY_INFO_ID        char(32)                       null,
   SURREND_NO           varchar2(32)                   null,
   POLICY_ID            char(32)                       null,
   POLICY_CODE          varchar2(32)                   null,
   SURREND_TYPE         varchar2(2)                    null,
   APPLY_CUSTOMER_ID    char(32)                       null,
   REPAY_AMOUNT         number(15,2)                   null,
   PRINCIPAL            number(15,2)                   null,
   TOTAL_REVENUE        number(15,2)                   null,
   HANDFEE              number(15,2)                   null,
   REPAY_TIME           date                           null,
   STATUS               varchar2(2)                    null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_surrend_apply is 
'退保申请';

comment on column t_surrend_apply.SURREND_NO is 
'退保单号码';

comment on column t_surrend_apply.POLICY_CODE is 
'保单号码';

comment on column t_surrend_apply.SURREND_TYPE is 
'退保类型';

comment on column t_surrend_apply.APPLY_CUSTOMER_ID is 
'申请人';

comment on column t_surrend_apply.PRINCIPAL is 
'本金';

comment on column t_surrend_apply.TOTAL_REVENUE is 
'收益';

comment on column t_surrend_apply.HANDFEE is 
'手续费';

comment on column t_surrend_apply.REPAY_TIME is 
'还款时间';
alter table t_surrend_apply add constraint PK_t_surrend_apply primary key (SURREND_APPLY_ID);

create sequence SEQ_t_surrend_apply minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_surrend_apply BEFORE INSERT ON t_surrend_apply FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_surrend_apply.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table t_loan_apply 
(
   ID                   number                         not null,
   LOAN_APPLY_ID        char(32)                       not null,
   APPLY_INFO_ID        char(32)                       null,
   LOAN_NO              varchar2(32)                   null,
   POLICY_ID            char(32)                       null,
   POLICY_CODE          varchar2(32)                   null,
   LOAN_TYPE            varchar2(2)                    null,
   APPLY_CUSTOMER_ID    char(32)                       null,
   APPLY_AMOUNT         number(15,2)                   null,
   POLICY_VALUE         number(15,2)                   null,
   LOAN_RATE            number(15,8)                   null,
   HANDFEE              number(15,2)                   null,
   ACTUAL_LOAN_AMOUNT   number(15,2)                   null,
   LOAN_BEGIN_DATE      date                           null,
   LOAN_END_DATE        date                           null,
   PAYMENT_TIME         date                       null,
   INSURANCE_COMPANY    char(32)                       null,
   LOAN_COMPANY         char(32)                       null,
   STATUS               varchar2(2)                    null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_loan_apply is 
'借款申请';

comment on column t_loan_apply.LOAN_NO is 
'借款号码';

comment on column t_loan_apply.POLICY_CODE is 
'保单号码';

comment on column t_loan_apply.LOAN_TYPE is 
'退保类型';

comment on column t_loan_apply.APPLY_CUSTOMER_ID is 
'申请人';

comment on column t_loan_apply.APPLY_AMOUNT is 
'申请借款金额';

comment on column t_loan_apply.POLICY_VALUE is 
'保单价值';

comment on column t_loan_apply.LOAN_RATE is 
'借款利息';

comment on column t_loan_apply.HANDFEE is 
'手续费';

comment on column t_loan_apply.ACTUAL_LOAN_AMOUNT is 
'实际借款金额';

comment on column t_loan_apply.LOAN_BEGIN_DATE is 
'计息开始时间';

comment on column t_loan_apply.LOAN_END_DATE is 
'计息结束时间';

comment on column t_loan_apply.PAYMENT_TIME is 
'打款时间';

comment on column t_loan_apply.INSURANCE_COMPANY is 
'保险公司';

comment on column t_loan_apply.LOAN_COMPANY is 
'放款公司';


alter table t_loan_apply add constraint PK_t_loan_apply primary key (LOAN_APPLY_ID);

create sequence SEQ_t_loan_apply minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_loan_apply BEFORE INSERT ON t_loan_apply FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_loan_apply.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table t_repay_apply 
(
   ID                   number                         not null,
   REPAY_APPLY_ID       char(32)                       not null,
   APPLY_INFO_ID        char(32)                       null,
   REPAY_NO             varchar2(32)                   null,
   LOAN_APPLY_ID        char(32)                       null,
   POLICY_ID            char(32)                       null,
   POLICY_CODE          varchar2(32)                   null,
   REPAY_TYPE           varchar2(2)                    null,
   APPLY_CUSTOMER_ID    char(32)                       null,
   APPLY_AMOUNT         number(15,2)                   null,
   REPAY_VALUE          number(15,2)                   null,
   REPAY_RATE           number(15,8)                   null,
   HANDFEE              number(15,2)                   null,
   ACTUAL_REPAY_AMOUNT  number(15,2)                   null,
   REPAY_TIME           date                           null,
   INSURANCE_COMPANY    char(32)                       null,
   LOAN_COMPANY         char(32)                       null,
   STATUS               varchar2(2)                    null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_repay_apply is 
'还款申请';

comment on column t_repay_apply.REPAY_NO is 
'还款号码';

comment on column t_repay_apply.POLICY_CODE is 
'保单号码';

comment on column t_repay_apply.REPAY_TYPE is 
'还款类型';

comment on column t_repay_apply.APPLY_CUSTOMER_ID is 
'申请人';

comment on column t_repay_apply.APPLY_AMOUNT is 
'申请还款金额';

comment on column t_repay_apply.REPAY_VALUE is 
'本金';

comment on column t_repay_apply.REPAY_RATE is 
'借款利息';

comment on column t_repay_apply.HANDFEE is 
'手续费';

comment on column t_repay_apply.ACTUAL_REPAY_AMOUNT is 
'实际还款金额';

comment on column t_repay_apply.REPAY_TIME is 
'还款时间';

comment on column t_repay_apply.INSURANCE_COMPANY is 
'保险公司';

comment on column t_repay_apply.LOAN_COMPANY is 
'放款公司';

alter table t_repay_apply add constraint PK_t_repay_apply primary key (REPAY_APPLY_ID);

create sequence SEQ_t_repay_apply minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_repay_apply BEFORE INSERT ON t_repay_apply FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_repay_apply.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table t_insurance 
(
   ID                   number                         not null,
   INSURANCE_ID         char(32)                       null,
   INSURANCE_NAME       varchar2(64)                   null,
   INSURANCE_CODE       varchar2(32)                   null,
   INSURANCE_ABBR_NAME  varchar2(64)                   null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_insurance is 
'保险公司定义表';

comment on column t_insurance.INSURANCE_ID is 
'保险公司id';

comment on column t_insurance.INSURANCE_NAME is 
'保险公司名称';

comment on column t_insurance.INSURANCE_CODE is 
'保险公司编码';

comment on column t_insurance.INSURANCE_ABBR_NAME is 
'保险公司简称';

alter table t_insurance add constraint PK_t_insurance primary key (INSURANCE_ID);

create sequence SEQ_t_insurance minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_insurance BEFORE INSERT ON t_insurance FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_insurance.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

create table t_bank 
(
   ID                   number                         not null,
   BANK_ID              char(32)                       null,
   BANK_NAME            varchar2(64)                   null,
   BANK_CODE            varchar2(32)                   null,
   BANK_ABBR_NAME       varchar2(64)                   null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_bank is 
'银行';

comment on column t_bank.BANK_ID is 
'银行id';

comment on column t_bank.BANK_NAME is 
'银行名称';

comment on column t_bank.BANK_CODE is 
'银行编码';

comment on column t_bank.BANK_ABBR_NAME is 
'银行简称';

alter table t_bank add constraint PK_t_bank primary key (BANK_ID);

create sequence SEQ_t_bank minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_bank BEFORE INSERT ON t_bank FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_bank.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table t_business_trans 
(
   ID                   number                         not null,
   TRANS_ID             char(32)                       not null,
   BUSINESS_ID          char(32)                       null,
   TRANS_NO             varchar2(32)                   null,
   TRANS_TYPE           varchar2(2)                    null,
   TRANS_CHANNEL        varchar2(2)                    null,
   TRANS_TIME           date                           null,
   PAY_MONEY            number(15,2)                   null,
   STATUS               varchar2(2)                    null,
   RESULT_MSG           varchar2(500)                  null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_business_trans is 
'交易日志表';

comment on column t_business_trans.BUSINESS_ID is 
'业务ID';

comment on column t_business_trans.TRANS_NO is 
'交易编码';

comment on column t_business_trans.TRANS_TYPE is 
'交易类型';

comment on column t_business_trans.TRANS_CHANNEL is 
'交易方';

comment on column t_business_trans.TRANS_TIME is 
'交易时间';

comment on column t_business_trans.PAY_MONEY is 
'支付金额';

alter table t_business_trans add constraint PK_t_business_trans primary key (TRANS_ID);

create sequence SEQ_t_business_trans minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_business_trans BEFORE INSERT ON t_business_trans FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_business_trans.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;


create table t_validateCode(
id number,
validate_code_id char(32),
validate_type varchar2(2),
validate_account varchar2(100),
validate_code varchar2(12),
send_time date,
isvalid char(1),
create_date date,
update_date date，
code_type varchar2(10)
);

alter table t_validateCode add constraint PK_t_validateCode primary key (validate_code_id);

create sequence SEQ_t_validateCode minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_validateCode BEFORE INSERT ON t_validateCode FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_validateCode.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;
