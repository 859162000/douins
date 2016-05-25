create table T_INSURANCE_INTERFACE_CONFIG 
(
   ID                   NUMBER                         not null,
   CONFIG_ID            CHAR(32)                       null,
   INSURANCE_ID         CHAR(32)                       null,
   CONFIG_TYPE          VARCHAR2(2)                    null,
   CONFIG_SERVICE       VARCHAR2(60)                   null,
   INTERFACE_URL        VARCHAR2(200)                  null,
   TRANS_TYPE           VARCHAR2(3)                    null,
   ASYN                 VARCHAR2(2)                    null,
   ISVALID              CHAR(1)                        null,
   CREATE_DATE          DATE                           null,
   UPDATE_DATE          DATE                           null
);

comment on table T_INSURANCE_INTERFACE_CONFIG
  is '保险公司接口配置表';
comment on column T_INSURANCE_INTERFACE_CONFIG.INSURANCE_ID
  is '保险公司ID';
comment on column T_INSURANCE_INTERFACE_CONFIG.CONFIG_TYPE
  is '接口类型';
comment on column T_INSURANCE_INTERFACE_CONFIG.CONFIG_SERVICE
  is '接口服务';
comment on column T_INSURANCE_INTERFACE_CONFIG.INTERFACE_URL
  is '接口地址';
comment on column T_INSURANCE_INTERFACE_CONFIG.TRANS_TYPE
  is '交易类型';
comment on column T_INSURANCE_INTERFACE_CONFIG.ASYN
  is '交易处理方式';


alter table T_INSURANCE_INTERFACE_CONFIG add constraint PK_INTERFACE_CONFIG primary key (CONFIG_ID);

create sequence SEQ_INTERFACE_CONFIG minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_INTERFACE_CONFIG BEFORE INSERT ON T_INSURANCE_INTERFACE_CONFIG FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_INTERFACE_CONFIG.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;