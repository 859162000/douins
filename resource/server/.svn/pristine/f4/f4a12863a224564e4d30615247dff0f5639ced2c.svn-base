
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `T_APPLY_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `T_APPLY_INFO`;
CREATE TABLE `T_APPLY_INFO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APPLY_INFO_ID` varchar(32) NOT NULL,
  `POLICY_ID` varchar(32) NOT NULL,
  `APPLY_TYPE` varchar(32) NOT NULL COMMENT '申请类型SU-退保 LO-借款 RE-还款',
  `APPLY_TIME` datetime NOT NULL COMMENT '申请时间',
  `APPLY_CUSTOMER_ID` varchar(32) NOT NULL COMMENT '申请人',
  `VALIDATE_TIME` datetime DEFAULT NULL COMMENT '生效时间',
  `STATUS` char(2) NOT NULL,
  `ISVALID` char(1) DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ind_apply_id` (`APPLY_INFO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='申请汇总表';

-- ----------------------------
--  Table structure for `T_BANK`
-- ----------------------------
DROP TABLE IF EXISTS `T_BANK`;
CREATE TABLE `T_BANK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BANK_ID` varchar(32) NOT NULL COMMENT '银行id',
  `BANK_NAME` varchar(64) NOT NULL COMMENT '银行名称',
  `BANK_CODE` varchar(32) NOT NULL COMMENT '银行编码',
  `BANK_ABBR_NAME` varchar(64) NOT NULL COMMENT '银行简称',
  `ISVALID` char(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行';

-- ----------------------------
--  Table structure for `T_BUSINESS_TRANS`
-- ----------------------------
DROP TABLE IF EXISTS `T_BUSINESS_TRANS`;
CREATE TABLE `T_BUSINESS_TRANS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TRANS_ID` varchar(32) NOT NULL,
  `BUSINESS_ID` varchar(32) NOT NULL COMMENT '业务ID',
  `TRANS_NO` varchar(32) DEFAULT NULL COMMENT '交易编码',
  `TRANS_TYPE` varchar(2) NOT NULL COMMENT '交易类型',
  `TRANS_CHANNEL` varchar(2) NOT NULL COMMENT '交易方',
  `TRANS_TIME` datetime NOT NULL COMMENT '交易时间',
  `PAY_MONEY` decimal(15,2) DEFAULT NULL COMMENT '支付金额',
  `STATUS` varchar(2) NOT NULL,
  `RESULT_MSG` text,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='交易日志表';

-- ----------------------------
--  Table structure for `T_CUSTOMER`
-- ----------------------------
DROP TABLE IF EXISTS `T_CUSTOMER`;
CREATE TABLE `T_CUSTOMER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `NAME` varchar(64) NOT NULL COMMENT '姓名',
  `CARD_TYPE` varchar(2) NOT NULL COMMENT '证件类型',
  `CARD_NO` varchar(32) NOT NULL COMMENT '证件号码',
  `SEX` varchar(1) NOT NULL COMMENT '性别',
  `CARD_VALID_DATE` datetime NOT NULL COMMENT '证件有效期',
  `BIRTHDAY` datetime NOT NULL COMMENT '出生日期',
  `EMAIL` varchar(255) NOT NULL COMMENT '邮箱',
  `PHONE` varchar(20) DEFAULT '' COMMENT '电话',
  `MOBILEPHONE` varchar(20) NOT NULL COMMENT '手机',
  `POSTCODE` varchar(20) NOT NULL COMMENT '邮编',
  `ADDRESS` varchar(200) NOT NULL COMMENT '地址',
  `LIVE_REGION` varchar(16) NOT NULL COMMENT '居住地区',
  `JOB` varchar(50) NOT NULL COMMENT '工作类型',
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `CERTI_IS_VALID` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

-- ----------------------------
--  Table structure for `T_IDEA_FEEDBACK`
-- ----------------------------
DROP TABLE IF EXISTS `T_IDEA_FEEDBACK`;
CREATE TABLE `T_IDEA_FEEDBACK` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IDEA_FEEDBACK_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `CONTACT` varchar(60) DEFAULT NULL,
  `FEEDBACK_TIME` datetime DEFAULT NULL,
  `IDEA` varchar(2000) DEFAULT NULL,
  `ISVALID` varchar(1) DEFAULT NULL,
  `CREATE_DATE` time DEFAULT NULL,
  `UPDATE_DATE` time DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `T_INSURANCE`
-- ----------------------------
DROP TABLE IF EXISTS `T_INSURANCE`;
CREATE TABLE `T_INSURANCE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `INSURANCE_ID` varchar(32) NOT NULL COMMENT '保险公司id',
  `INSURANCE_NAME` varchar(64) NOT NULL COMMENT '保险公司名称',
  `INSURANCE_CODE` varchar(32) NOT NULL COMMENT '保险公司编码',
  `INSURANCE_ABBR_NAME` varchar(64) NOT NULL COMMENT '保险公司简称',
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `SIGN_KEY` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险公司定义表';

-- ----------------------------
--  Table structure for `T_INSURANCE_INTERFACE_CONFIG`
-- ----------------------------
DROP TABLE IF EXISTS `T_INSURANCE_INTERFACE_CONFIG`;
CREATE TABLE `T_INSURANCE_INTERFACE_CONFIG` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONFIG_ID` varchar(32) NOT NULL,
  `INSURANCE_ID` varchar(32) NOT NULL COMMENT '保险公司ID',
  `CONFIG_TYPE` varchar(2) NOT NULL COMMENT '接口类型',
  `CONFIG_SERVICE` varchar(60) NOT NULL COMMENT '接口服务',
  `INTERFACE_URL` varchar(200) NOT NULL COMMENT '接口地址',
  `TRANS_TYPE` varchar(3) NOT NULL COMMENT '交易类型',
  `ASYN` varchar(2) NOT NULL COMMENT '交易处理方式',
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险公司接口配置表';

-- ----------------------------
--  Table structure for `T_LOAN_APPLY`
-- ----------------------------
DROP TABLE IF EXISTS `T_LOAN_APPLY`;
CREATE TABLE `T_LOAN_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOAN_APPLY_ID` varchar(32) NOT NULL,
  `APPLY_INFO_ID` varchar(32) NOT NULL,
  `LOAN_NO` varchar(32) NOT NULL COMMENT '借款号码',
  `POLICY_ID` varchar(32) NOT NULL,
  `POLICY_CODE` varchar(32) NOT NULL COMMENT '保单号码',
  `LOAN_TYPE` varchar(2) NOT NULL COMMENT '退保类型',
  `APPLY_CUSTOMER_ID` varchar(32) NOT NULL COMMENT '申请人',
  `APPLY_AMOUNT` decimal(15,2) NOT NULL COMMENT '申请借款金额',
  `POLICY_VALUE` decimal(15,2) NOT NULL COMMENT '保单价值',
  `LOAN_RATE` decimal(15,8) NOT NULL COMMENT '借款利息',
  `HANDFEE` decimal(15,2) NOT NULL COMMENT '手续费',
  `ACTUAL_LOAN_AMOUNT` decimal(15,2) DEFAULT NULL COMMENT '实际借款金额',
  `LOAN_BEGIN_DATE` date NOT NULL COMMENT '计息开始时间',
  `LOAN_END_DATE` date NOT NULL COMMENT '计息结束时间',
  `PAYMENT_TIME` datetime DEFAULT NULL COMMENT '打款时间',
  `INSURANCE_COMPANY` varchar(32) NOT NULL COMMENT '保险公司',
  `LOAN_COMPANY` varchar(32) NOT NULL COMMENT '放款公司',
  `STATUS` varchar(2) NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `CYCLE` decimal(12,2) NOT NULL,
  `UNIT` varchar(2) NOT NULL,
  `LOAN_INTEREST` decimal(16,2) NOT NULL,
  `TOTAL_REPAY_AMOUNT` decimal(24,8) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='借款申请';

-- ----------------------------
--  Table structure for `T_PAYMENT_APPLY`
-- ----------------------------
DROP TABLE IF EXISTS `T_PAYMENT_APPLY`;
CREATE TABLE `T_PAYMENT_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PAYMENT_APPLY_ID` varchar(32) NOT NULL,
  `POLICY_ID` varchar(32) NOT NULL,
  `USER_ACCOUNT_ID` varchar(32) NOT NULL COMMENT '虚拟账户ID',
  `PAY_ORDER_NO` varchar(32) NOT NULL COMMENT '支付编码',
  `PAY_TYPE` varchar(2) NOT NULL,
  `THIRDPAY_TYPE` varchar(2) NOT NULL COMMENT '第三方支付',
  `FROM_ACCOUNT_NAME` varchar(64) NOT NULL COMMENT '账户名',
  `FROM_ACCOUNT_NO` varchar(32) NOT NULL COMMENT '账户号码',
  `FROM_BANK_CODE` varchar(32) NOT NULL,
  `FROM_BANK_NAME` varchar(64) DEFAULT NULL,
  `TO_ACCOUNT_NAME` varchar(64) NOT NULL,
  `TO_ACCOUNT_NO` varchar(32) NOT NULL,
  `TO_BANK_CODE` varchar(32) NOT NULL,
  `TO_BANK_NAME` varchar(64) DEFAULT NULL,
  `PAY_APPLY_TIME` datetime NOT NULL COMMENT '申请支付时间',
  `PAY_TIME` datetime DEFAULT NULL COMMENT '支付完成时间',
  `PAY_MONEY` decimal(15,2) NOT NULL COMMENT '支付金额',
  `STATUS` varchar(2) NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='支付信息';

-- ----------------------------
--  Table structure for `T_POLICY_MASTER`
-- ----------------------------
DROP TABLE IF EXISTS `T_POLICY_MASTER`;
CREATE TABLE `T_POLICY_MASTER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POLICY_ID` varchar(32) DEFAULT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `INSURANCE_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `ORDER_NO` varchar(32) NOT NULL COMMENT '订单编码',
  `PAY_ORDER_NO` varchar(32) NOT NULL COMMENT '支付订单编码',
  `STATUS` varchar(2) NOT NULL COMMENT '状态',
  `APPLY_TIME` datetime NOT NULL COMMENT '下单时间',
  `UNDERWRITIND_TIME` datetime DEFAULT NULL COMMENT '核保时间',
  `PAY_TIME` datetime DEFAULT NULL COMMENT '支付时间',
  `VALIDATE_DATE` datetime DEFAULT NULL COMMENT '生效时间',
  `EXPIRE_DATE` datetime DEFAULT NULL COMMENT '到期时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '赎回时间',
  `APPLICANT_ID` varchar(32) NOT NULL COMMENT '投保人ID',
  `INSURED_ID` varchar(32) NOT NULL COMMENT '被保险人ID',
  `APPLY_NUM` decimal(12,0) NOT NULL COMMENT '购买份数',
  `TOTAL_PREM` decimal(15,2) NOT NULL COMMENT '总保费',
  `TOTAL_REVENUE` decimal(15,2) DEFAULT NULL COMMENT '预期总收益',
  `PREM` decimal(15,2) NOT NULL COMMENT '单价',
  `REPAY_AMOUNT` decimal(15,2) DEFAULT NULL COMMENT '预期赎回金额',
  `SEND_CODE` varchar(32) DEFAULT NULL COMMENT '保险公司投保单号码',
  `POLICY_CODE` varchar(32) DEFAULT NULL COMMENT '保险公司保单号码',
  `COMMENTS` varchar(200) DEFAULT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `POLICY_URL` varchar(300) DEFAULT NULL COMMENT '电子保单URL',
  `UPDATE_DATE` datetime NOT NULL,
  `RELATION` varchar(2) DEFAULT NULL COMMENT '投被保险人关系',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='保单表';

-- ----------------------------
--  Table structure for `T_PRODUCT`
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCT`;
CREATE TABLE `T_PRODUCT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` varchar(32) NOT NULL COMMENT '产品id',
  `INSURANCE_ID` varchar(32) NOT NULL COMMENT '保险公司id',
  `PRODUCT_NAME` varchar(100) NOT NULL COMMENT '产品名称',
  `PRODUCT_DESC` varchar(2000) DEFAULT NULL COMMENT '产品简介',
  `STATUS` varchar(2) NOT NULL,
  `PRODUCT_CODE` varchar(30) NOT NULL COMMENT '保险公司产品编码',
  `PARTNER_PRODUCT_CODE` varchar(32) NOT NULL COMMENT '保险公司内部产品编码',
  `PARTNER_CODE` varchar(32) NOT NULL COMMENT '保险公司编码',
  `PRODUCT_TYPE` varchar(32) NOT NULL COMMENT '产品类型',
  `PRODUCT_INCOME_DESC` varchar(300) DEFAULT NULL COMMENT '产品收益说明',
  `PRODUCT_RISK_DESC` varchar(300) DEFAULT NULL COMMENT '产品风险说明',
  `RISK_LEVEL` varchar(2) NOT NULL COMMENT '风险等级',
  `AGE_UPPER` smallint(3) NOT NULL COMMENT '限购年龄上限',
  `AGE_FLOOR` smallint(3) NOT NULL COMMENT '限购年龄下限',
  `AREA_LIMIT` varchar(300) DEFAULT NULL COMMENT '限购区域',
  `PRODUCT_PERIOD` decimal(5,0) NOT NULL COMMENT '产品期限',
  `PERIOD_TYPE` varchar(2) NOT NULL COMMENT '期限类型',
  `EXPIRE_DATE` date DEFAULT NULL COMMENT '到期日',
  `REPAY_DATE` date DEFAULT NULL COMMENT '还款日',
  `INCOME_TYPE` varchar(2) NOT NULL COMMENT '收益类型',
  `EXPECT_RATE` decimal(12,4) NOT NULL COMMENT '预期年化收益率',
  `MIN_RATE` decimal(12,4) NOT NULL COMMENT '保底年化收益率',
  `STOCK` int(20) NOT NULL COMMENT '可售份额',
  `UNIT_PREM` decimal(15,2) NOT NULL COMMENT '每份保费',
  `MIN_PREM` decimal(15,2) NOT NULL COMMENT '单笔最小保费',
  `MAX_PREM` decimal(15,2) NOT NULL COMMENT '单笔最大保费',
  `MAX_PER_PREM` decimal(15,2) NOT NULL COMMENT '单人最大保费',
  `SALE_BEGIN_TIME` datetime DEFAULT NULL COMMENT '起售时间',
  `SALE_END_TIME` datetime DEFAULT NULL COMMENT '下架时间',
  `DEFAULT_RENEW_TYPE` varchar(2) DEFAULT NULL COMMENT '默认到期处理方式',
  `RENEW_TYPE` varchar(32) DEFAULT NULL COMMENT '支持的到期处理方式',
  `AHEAD_REPAY_FALG` varchar(1) NOT NULL COMMENT '是否可以提前赎回',
  `AHEAD_REPAY_HANDFEE` decimal(12,4) NOT NULL COMMENT '提前赎回手续费率',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  `ISVALID` varchar(1) NOT NULL COMMENT '是否有效',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `SALE_NUM` int(20) NOT NULL,
  `PRODUCT_COVERAGE` int(4) DEFAULT NULL,
  `COVERAGE_TYPE` varchar(2) DEFAULT NULL,
  `IS_RECOMMEND` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否首页推荐的标志',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='产品定义表';

-- ----------------------------
--  Table structure for `T_PRODUCT_DETAIL`
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCT_DETAIL`;
CREATE TABLE `T_PRODUCT_DETAIL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PRODUCT_DETAIL_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `PRODUCT_AMOUNT` decimal(18,2) NOT NULL COMMENT '产品规模',
  `PRODUCT_INSURE_NAME` varchar(200) NOT NULL,
  `RECEIVE_TIME_DESC` varchar(200) DEFAULT NULL COMMENT '到期时间',
  `RECEIVE_TYPE` varchar(200) NOT NULL COMMENT '领取方式',
  `PRODUCT_DESC` varchar(900) NOT NULL COMMENT '产品介绍',
  `BUY_DESC` varchar(900) NOT NULL COMMENT '购买说明',
  `PRODUCT_BUY_DESC` varchar(900) NOT NULL COMMENT '产品提示',
  `RISK_DESC` varchar(2000) NOT NULL COMMENT '产品风险提示',
  `ISVALID` varchar(1) NOT NULL COMMENT '是否有效',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='产品介绍表';

-- ----------------------------
--  Table structure for `T_PRODUCT_LOAN_CYCLE`
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCT_LOAN_CYCLE`;
CREATE TABLE `T_PRODUCT_LOAN_CYCLE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOAN_CYCLE_ID` varchar(32) NOT NULL,
  `PRODUCT_ID` varchar(32) NOT NULL,
  `UNIT` varchar(2) NOT NULL COMMENT '周期类型',
  `CYCLE` decimal(12,2) NOT NULL COMMENT '周期',
  `RATE` decimal(12,4) NOT NULL COMMENT '利率',
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='贷款利率定义表';

-- ----------------------------
--  Table structure for `T_REPAY_APPLY`
-- ----------------------------
DROP TABLE IF EXISTS `T_REPAY_APPLY`;
CREATE TABLE `T_REPAY_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REPAY_APPLY_ID` varchar(32) NOT NULL,
  `APPLY_INFO_ID` varchar(32) NOT NULL,
  `REPAY_NO` varchar(32) NOT NULL COMMENT '还款号码',
  `LOAN_APPLY_ID` varchar(32) NOT NULL,
  `POLICY_ID` varchar(32) NOT NULL,
  `POLICY_CODE` varchar(32) NOT NULL COMMENT '保单号码',
  `REPAY_TYPE` varchar(2) NOT NULL COMMENT '还款类型',
  `APPLY_CUSTOMER_ID` varchar(32) NOT NULL COMMENT '申请人',
  `APPLY_AMOUNT` decimal(15,2) NOT NULL COMMENT '申请还款金额',
  `REPAY_VALUE` decimal(15,2) NOT NULL COMMENT '本金',
  `REPAY_RATE` decimal(15,8) NOT NULL COMMENT '借款利息',
  `HANDFEE` decimal(15,2) NOT NULL COMMENT '手续费',
  `ACTUAL_REPAY_AMOUNT` decimal(15,2) NOT NULL COMMENT '实际还款金额',
  `REPAY_TIME` datetime DEFAULT NULL COMMENT '还款时间',
  `INSURANCE_COMPANY` varchar(32) NOT NULL COMMENT '保险公司',
  `LOAN_COMPANY` varchar(32) NOT NULL COMMENT '放款公司',
  `STATUS` varchar(2) NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='还款申请';

-- ----------------------------
--  Table structure for `T_REQUEST_TRANS`
-- ----------------------------
DROP TABLE IF EXISTS `T_REQUEST_TRANS`;
CREATE TABLE `T_REQUEST_TRANS` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REQUEST_TRANS_ID` varchar(32) NOT NULL,
  `TRANS_NO` varchar(32) NOT NULL COMMENT '交易号',
  `TRANS_ID` varchar(32) NOT NULL COMMENT '流水号',
  `TRANS_TYPE` varchar(3) NOT NULL COMMENT '交易类型',
  `TRANS_CHANNEL` varchar(2) NOT NULL COMMENT '交易渠道',
  `TRANS_TIME` datetime NOT NULL COMMENT '交易时间',
  `REQUEST_TIME` datetime NOT NULL COMMENT '请求时间',
  `IP_ADDRESS` varchar(32) NOT NULL COMMENT 'IP地址',
  `CLIENT_TYPE` varchar(20) NOT NULL COMMENT '客户端类型',
  `RESPONSE_CODE` varchar(10) NOT NULL,
  `RESPONSE_MSG` varchar(200) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `T_SMS_SEND`
-- ----------------------------
DROP TABLE IF EXISTS `T_SMS_SEND`;
CREATE TABLE `T_SMS_SEND` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SMS_ID` varchar(32) NOT NULL COMMENT 'SMS_ID',
  `TRANSTYPE` varchar(3) NOT NULL COMMENT '短信类型',
  `SMS_TEMPLATE_ID` varchar(10) NOT NULL COMMENT '短信模板ID',
  `SMS_CONTENT` varchar(500) NOT NULL COMMENT '短信内容',
  `MOBILE` varchar(200) NOT NULL COMMENT '发送手机号码',
  `SMS_APPLY` varchar(20) NOT NULL COMMENT '发送短信通道',
  `SMS_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `SMS_STATUS` varchar(2) NOT NULL,
  `RESULT_MSG` varchar(500) DEFAULT NULL,
  `ISVALID` varchar(1) NOT NULL COMMENT '是否有效',
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `CHECKCODE` varchar(64) DEFAULT NULL COMMENT '验证码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='短信记录表';

-- ----------------------------
--  Table structure for `T_SURREND_APPLY`
-- ----------------------------
DROP TABLE IF EXISTS `T_SURREND_APPLY`;
CREATE TABLE `T_SURREND_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SURREND_APPLY_ID` varchar(32) NOT NULL,
  `APPLY_INFO_ID` varchar(32) NOT NULL,
  `SURREND_NO` varchar(32) NOT NULL COMMENT '退保单号码',
  `POLICY_ID` varchar(32) NOT NULL,
  `POLICY_CODE` varchar(32) NOT NULL COMMENT '保单号码',
  `SURREND_TYPE` varchar(2) NOT NULL COMMENT '退保类型',
  `APPLY_CUSTOMER_ID` varchar(32) NOT NULL COMMENT '申请人',
  `REPAY_AMOUNT` decimal(15,2) NOT NULL,
  `PRINCIPAL` decimal(15,2) NOT NULL COMMENT '本金',
  `TOTAL_REVENUE` decimal(15,2) NOT NULL COMMENT '收益',
  `HANDFEE` decimal(15,2) NOT NULL COMMENT '手续费',
  `REPAY_TIME` datetime DEFAULT NULL COMMENT '还款时间',
  `STATUS` varchar(2) NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='退保申请';

-- ----------------------------
--  Table structure for `T_USER`
-- ----------------------------
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` varchar(32) NOT NULL COMMENT 'USER_ID',
  `USER_TYPE` varchar(1) NOT NULL DEFAULT '1' COMMENT '个人/企业',
  `USER_ACCOUNT` varchar(64) NOT NULL COMMENT '账户',
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '账户类型',
  `LOGIN_PASSWORD` varchar(64) NOT NULL COMMENT '登录密码',
  `USER_EMAIL` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `USER_MOBILEPHONE` varchar(15) DEFAULT NULL COMMENT '手机',
  `REGISTER_TYPE` varchar(2) NOT NULL COMMENT '来源',
  `REGISTER_TIME` datetime NOT NULL COMMENT '注册时间',
  `USER_NICKNAME` varchar(64) DEFAULT NULL COMMENT '昵称',
  `STATUS` varchar(2) NOT NULL COMMENT '状态',
  `LAST_LOGIN_TIME` datetime NOT NULL COMMENT '最后登录时间',
  `IP_ADDRESS` varchar(32) NOT NULL COMMENT '登录IP',
  `NOVICE` varchar(1) NOT NULL COMMENT '是否新用户',
  `USER_GRADE` varchar(2) DEFAULT NULL COMMENT '用户等级',
  `PAY_PASSWORD` varchar(64) DEFAULT NULL COMMENT '支付密码',
  `GESTURE_PASSWORD` varchar(64) DEFAULT NULL COMMENT '手势密码',
  `USER_NAME` varchar(64) DEFAULT NULL COMMENT '姓名',
  `USER_SEX` varchar(1) DEFAULT NULL COMMENT '性别',
  `USER_BIRTHDAY` date DEFAULT NULL COMMENT '出生日期',
  `CERTI_TYPE` varchar(2) DEFAULT NULL COMMENT '证件类型',
  `CERTI_CODE` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `FETCH_TYPE` varchar(2) DEFAULT NULL COMMENT '取回密码方式',
  `FETCH_TIME` datetime DEFAULT NULL COMMENT '取回密码时间',
  `ISVALID` varchar(1) NOT NULL COMMENT '有效状态',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新时间',
  `OPUSER` varchar(32) NOT NULL COMMENT '操作人',
  `PROVINCE` varchar(32) DEFAULT NULL COMMENT '省',
  `CITY` varchar(32) DEFAULT NULL COMMENT '市',
  `DISTRICT` varchar(32) DEFAULT NULL COMMENT '区',
  `DETAILED_ADRESS` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `ZIPCODE` varchar(6) DEFAULT NULL COMMENT '邮编',
  `SWITCH_PAYPASSWORD` varchar(1) DEFAULT NULL COMMENT '支付密码开关  1 已初始化，0未初始化',
  `SWITCH_GESTUREPASSWORD` varchar(1) DEFAULT NULL COMMENT '手势密码开关  1 已初始化，0未初始化',
  `OPEN_ID` varchar(50) DEFAULT NULL,
  `CERTI_VALID_DATE` datetime DEFAULT NULL,
  `CERTI_IS_VALID` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
--  Table structure for `T_USER_ACCOUNT`
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_ACCOUNT`;
CREATE TABLE `T_USER_ACCOUNT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ACCOUNT_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `ACCOUNT_AMOUNT` decimal(24,8) NOT NULL COMMENT '账户总价值',
  `POINT` decimal(16,2) NOT NULL COMMENT '积分值',
  `ACCOUNT_BALANCE` decimal(24,8) NOT NULL COMMENT '账户现金余额',
  `POLICY_VALUE` decimal(24,8) NOT NULL COMMENT '保单价值',
  `LOAN_AMOUNT` decimal(24,8) NOT NULL COMMENT '借款金额',
  `VIRTUAL_ACCOUNT_NO` varchar(20) DEFAULT NULL COMMENT '虚拟账户号码',
  `SURR_AMOUNT` decimal(24,8) NOT NULL COMMENT '退保金额',
  `REPAY_AMOUNT` decimal(24,8) NOT NULL COMMENT '还款金额',
  `STATUS` varchar(2) NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户虚拟账户表';

-- ----------------------------
--  Table structure for `T_USER_ACCOUNT_DETAIL`
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_ACCOUNT_DETAIL`;
CREATE TABLE `T_USER_ACCOUNT_DETAIL` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ACCOUNT_DETAIL_ID` varchar(32) NOT NULL,
  `USER_ACCOUNT_ID` varchar(32) NOT NULL,
  `BUSINESS_ID` varchar(32) NOT NULL,
  `DETAIL_TYPE` varchar(4) NOT NULL COMMENT '明细类型1-充值 2-提现 3-购买保单 4-保单借款打款  5-保单还款',
  `DETAIL_IO` varchar(2) NOT NULL COMMENT '进出类型1-入账 -1-出账',
  `FROM_ACCOUNT_NAME` varchar(64) DEFAULT NULL COMMENT '账户名',
  `FROM_ACCOUNT_NO` varchar(32) DEFAULT NULL COMMENT '账户号码',
  `FROM_BANK_CODE` varchar(32) DEFAULT NULL COMMENT '银行编码',
  `FROM_BANK_NAME` varchar(64) DEFAULT NULL COMMENT '银行名称',
  `TO_ACCOUNT_NAME` varchar(64) DEFAULT NULL,
  `TO_ACCOUNT_NO` varchar(32) DEFAULT NULL,
  `TO_BANK_CODE` varchar(32) DEFAULT NULL,
  `TO_BANK_NAME` varchar(64) NOT NULL,
  `APPLY_TIME` datetime NOT NULL COMMENT '申请时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '完成时间',
  `APPLY_AMOUNT` decimal(15,2) NOT NULL COMMENT '金额',
  `STATUS` varchar(2) NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `BUSINESS_NO` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='虚拟账户明细';

-- ----------------------------
--  Table structure for `T_USER_ACCOUNT_OPEN_APPLY`
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_ACCOUNT_OPEN_APPLY`;
CREATE TABLE `T_USER_ACCOUNT_OPEN_APPLY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OPEN_APPLY_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `ACCOUNT_ID` varchar(32) NOT NULL,
  `STATUS` varchar(2) NOT NULL COMMENT '状态',
  `VIRTUAL_ACCOUNT_NO` varchar(20) DEFAULT NULL COMMENT '虚拟账户号码',
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='客户开发申请表';

-- ----------------------------
--  Table structure for `T_USER_PAYACCOUNT`
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_PAYACCOUNT`;
CREATE TABLE `T_USER_PAYACCOUNT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ACCOUNT_ID` varchar(32) NOT NULL COMMENT '支付账号ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '客户ID',
  `USER_ACCOUNT_ID` varchar(32) DEFAULT NULL,
  `ACCOUNT_TYPE` varchar(1) NOT NULL COMMENT '账号类型 1银行卡，2微信，3支付宝',
  `ACCOUNT_NAME` varchar(100) NOT NULL COMMENT '账户名称（银行名称）、支付宝、微信',
  `ACCOUNT_NO` varchar(30) NOT NULL COMMENT '账户号（银行卡号）、支付宝账号、微信账号',
  `BANK_PROVINCE` varchar(50) NOT NULL COMMENT '开户行所在省',
  `BANK_CITY` varchar(50) NOT NULL COMMENT '开户行所在市',
  `BANK_BRANCH` varchar(64) DEFAULT NULL COMMENT '分行名称',
  `BANK_CODE` varchar(10) NOT NULL COMMENT '银行编码',
  `CARDHOLDER_NAME` varchar(32) NOT NULL COMMENT '持卡人姓名',
  `CARDHOLDER_CERTICODE` varchar(32) NOT NULL COMMENT '持卡人身份证',
  `CARDHOLDER_CERTITYPE` varchar(2) NOT NULL COMMENT '证件类型',
  `BANKMOBILE` varchar(15) NOT NULL COMMENT '银行预留手机',
  `STATUS` varchar(2) NOT NULL,
  `OPUSER` varchar(64) DEFAULT NULL COMMENT '操作人',
  `ISVALID` varchar(1) NOT NULL COMMENT '是否有效',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='客户支付账号表';

-- ----------------------------
--  Table structure for `T_VALIDATECODE`
-- ----------------------------
DROP TABLE IF EXISTS `T_VALIDATECODE`;
CREATE TABLE `T_VALIDATECODE` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VALIDATE_CODE_ID` varchar(32) NOT NULL,
  `VALIDATE_TYPE` varchar(2) NOT NULL,
  `VALIDATE_ACCOUNT` varchar(100) NOT NULL,
  `VALIDATE_CODE` varchar(12) NOT NULL,
  `SEND_TIME` datetime NOT NULL,
  `ISVALID` varchar(1) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `CODE_TYPE` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
