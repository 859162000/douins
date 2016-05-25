create table t_product 
(
   ID                   number                         not null,
   PRODUCT_ID           char(32)                       not null,
   INSURANCE_ID         CHAR(32)                       null,
   PRODUCT_NAME         varchar2(100)                  null,
   PRODUCT_DESC         Long                           null,
   STATUS               varchar2(2)                    null,
   PRODUCT_CODE         varchar2(30)                   null,
   PARTNER_PRODUCT_CODE varchar2(32)                   null,
   PARTNER_CODE         varchar2(32)                   null,
   PRODUCT_TYPE         varchar2(32)                   null,
   PRODUCT_INCOME_DESC  varchar2(300)                  null,
   PRODUCT_RISK_DESC    varchar2(300)                  null,
   RISK_LEVEL           varchar2(2)                    null,
   AGE_UPPER            number(3)                      null,
   AGE_FLOOR            number(3)                      null,
   AREA_LIMIT           varchar2(300)                  null,
   PRODUCT_PERIOD       number(5)                      null,
   PERIOD_TYPE          varchar2(2)                    null,
   EXPIRE_DATE          date                           null,
   REPAY_DATE           date                           null,
   INCOME_TYPE          varchar2(2)                    null,
   EXPECT_RATE          number(12,4)                   null,
   MIN_RATE             number(12,4)                   null,
   STOCK                number(12)                     null,
   UNIT_PREM            number(15,2)                   null,
   MIN_PREM             number(15,2)                   null,
   MAX_PREM             number(15,2)                   null,
   MAX_PER_PREM         number(15,2)                   null,
   SALE_BEGIN_TIME      date                           null,
   SALE_END_TIME        date                           null,
   DEFAULT_RENEW_TYPE   varchar2(2)                    null,
   RENEW_TYPE           varchar2(32)                   null,
   AHEAD_REPAY_FALG     varchar2(1)                    null,
   AHEAD_REPAY_HANDFEE  number(12,4)                   null,
   REMARK               varchar2(300)                  null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_product is 
'产品定义表';

comment on column t_product.PRODUCT_ID is 
'产品id';

comment on column t_product.INSURANCE_ID is 
'保险公司id';

comment on column t_product.PRODUCT_NAME is 
'产品名称';

comment on column t_product.PRODUCT_DESC is 
'产品简介';

comment on column t_product.PRODUCT_CODE is 
'保险公司产品编码';

comment on column t_product.PARTNER_PRODUCT_CODE is 
'保险公司内部产品编码';

comment on column t_product.PARTNER_CODE is 
'保险公司编码';

comment on column t_product.PRODUCT_TYPE is 
'产品类型';

comment on column t_product.PRODUCT_INCOME_DESC is 
'产品收益说明';

comment on column t_product.PRODUCT_RISK_DESC is 
'产品风险说明';

comment on column t_product.RISK_LEVEL is 
'风险等级';

comment on column t_product.AGE_UPPER is 
'限购年龄上限';

comment on column t_product.AGE_FLOOR is 
'限购年龄下限';

comment on column t_product.AREA_LIMIT is 
'限购区域';

comment on column t_product.PRODUCT_PERIOD is 
'产品期限';

comment on column t_product.PERIOD_TYPE is 
'期限类型';

comment on column t_product.EXPIRE_DATE is 
'到期日';

comment on column t_product.REPAY_DATE is 
'还款日';

comment on column t_product.INCOME_TYPE is 
'收益类型';

comment on column t_product.EXPECT_RATE is 
'预期年化收益率';

comment on column t_product.MIN_RATE is 
'保底年化收益率';

comment on column t_product.STOCK is 
'可售份额';

comment on column t_product.UNIT_PREM is 
'每份保费';

comment on column t_product.MIN_PREM is 
'单笔最小保费';

comment on column t_product.MAX_PREM is 
'单笔最大保费';

comment on column t_product.MAX_PER_PREM is 
'单人最大保费';

comment on column t_product.SALE_BEGIN_TIME is 
'起售时间';

comment on column t_product.SALE_END_TIME is 
'下架时间';

comment on column t_product.DEFAULT_RENEW_TYPE is 
'默认到期处理方式';

comment on column t_product.RENEW_TYPE is 
'支持的到期处理方式';

comment on column t_product.AHEAD_REPAY_FALG is 
'是否可以提前赎回';

comment on column t_product.AHEAD_REPAY_HANDFEE is 
'提前赎回手续费率';

comment on column t_product.REMARK is 
'备注';

comment on column t_product.ISVALID is 
'是否有效';


alter table t_product add constraint PK_t_product primary key (PRODUCT_ID);

create sequence SEQ_t_product minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_product BEFORE INSERT ON t_product FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_product.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;



create table t_product_loan_cycle 
(
   ID                   number                         not null,
   LOAN_CYCLE_ID        char(32)                       null,
   PRODUCT_ID           CHAR(32)                       null,
   UNIT                 VARCHAR2(2)                    null,
   CYCLE                NUMBER(12,2)                   null,
   RATE                 number(12,4)                   null,
   ISVALID              char(1)                        null,
   CREATE_DATE          date                           null,
   UPDATE_DATE          date                           null
);

comment on table t_product_loan_cycle is 
'贷款利率定义表';

comment on column t_product_loan_cycle.UNIT is 
'周期类型';

comment on column t_product_loan_cycle.CYCLE is 
'周期';

comment on column t_product_loan_cycle.RATE is 
'利率';

alter table t_product_loan_cycle add constraint PK_t_product_loan_cycle primary key (LOAN_CYCLE_ID);

create sequence SEQ_t_product_loan_cycle minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 nocache;

CREATE OR REPLACE TRIGGER TB_t_product_loan_cycle BEFORE INSERT ON t_product_loan_cycle FOR EACH ROW DECLARE NEWID NUMBER(20);
BEGIN
  SELECT SEQ_t_product_loan_cycle.NEXTVAL INTO NEWID FROM DUAL;
  :NEW.ID := NEWID;
END;