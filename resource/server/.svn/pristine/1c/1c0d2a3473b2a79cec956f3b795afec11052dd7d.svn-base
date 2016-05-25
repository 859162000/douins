-- Create table
create table T_PRODUCT_DETAIL
(
  ID              NUMBER,
  PRODUCT_DETAIL_ID CHAR(32),
  PRODUCT_ID          CHAR(32),
  PRODUCT_AMOUNT      NUMBER(18,2),
  PRODUCT_INSURE_NAME VARCHAR2(200),
  RECEIVE_TIME_DESC        VARCHAR2(200),
  RECEIVE_TYPE     VARCHAR2(200),
  PRODUCT_DESC          VARCHAR2(900),
  BUY_DESC       VARCHAR2(900),
  PRODUCT_BUY_DESC        VARCHAR2(900),
  RISK_DESC      VARCHAR2(2000),
  ISVALID         CHAR(1),
  CREATE_DATE     DATE,
  UPDATE_DATE     DATE
);
alter table T_PRODUCT_DETAIL add constraint PK_T_PRODUCT_DETAIL primary key (PRODUCT_DETAIL_ID);

create sequence SEQ_T_PRODUCT_DETAIL minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_T_PRODUCT_DETAIL BEFORE INSERT ON T_PRODUCT_DETAIL FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_T_PRODUCT_DETAIL.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;

-- Add comments to the table 
comment on table T_PRODUCT_DETAIL
  is '产品介绍表';
-- Add comments to the columns 
comment on column T_PRODUCT_DETAIL.ID
  is 'ID';
comment on column T_PRODUCT_DETAIL.PRODUCT_AMOUNT
  is '产品规模';
comment on column T_PRODUCT_DETAIL.RECEIVE_TIME_DESC
  is '到期时间';
comment on column T_PRODUCT_DETAIL.RECEIVE_TYPE
  is '领取方式';
comment on column T_PRODUCT_DETAIL.PRODUCT_DESC
  is '产品介绍';
comment on column T_PRODUCT_DETAIL.BUY_DESC
  is '购买说明';
comment on column T_PRODUCT_DETAIL.PRODUCT_BUY_DESC
  is '产品提示';
comment on column T_PRODUCT_DETAIL.RISK_DESC
  is '产品提示';
comment on column T_PRODUCT_DETAIL.ISVALID
  is '是否有效';

