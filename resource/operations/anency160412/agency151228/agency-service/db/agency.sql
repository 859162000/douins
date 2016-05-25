/*
 Navicat MySQL Data Transfer

 Source Server         : douins
 Source Server Version : 50537
 Source Host           : 120.26.218.0
 Source Database       : agency

 Target Server Version : 50537
 File Encoding         : utf-8

 Date: 01/26/2016 18:15:30 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dt_insurecfm_fb`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecfm_fb`;
CREATE TABLE `dt_insurecfm_fb` (
  `Id` int(8) NOT NULL AUTO_INCREMENT,
  `tradeNo` varchar(32) DEFAULT NULL,
  `orderNo` varchar(32) NOT NULL,
  `applySeq` varchar(32) NOT NULL,
  `responseCode` varchar(8) DEFAULT NULL,
  `responseMessage` varchar(200) DEFAULT NULL,
  `printNo` varchar(22) DEFAULT NULL,
  `proposalNo` varchar(30) NOT NULL,
  `policyNo` varchar(30) NOT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `amount` varchar(10) DEFAULT NULL,
  `premium` varchar(10) DEFAULT NULL,
  `comCode` varchar(10) DEFAULT NULL,
  `comName` varchar(32) DEFAULT NULL,
  `comAddress` varchar(64) DEFAULT NULL,
  `comPostCode` varchar(8) DEFAULT NULL,
  `comPhoneNumber` varchar(18) DEFAULT NULL,
  `handler` varchar(20) DEFAULT NULL,
  `salesName` varchar(64) DEFAULT NULL,
  `appliName` varchar(10) DEFAULT NULL,
  `identifyNumber` varchar(20) DEFAULT NULL,
  `identifyType` varchar(2) DEFAULT NULL,
  `identifyPeriod` varchar(20) DEFAULT NULL,
  `birthDay` varchar(10) DEFAULT NULL,
  `age` varchar(3) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `insuredIdentity` varchar(2) DEFAULT NULL,
  `appliAddress` varchar(32) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `account` varchar(20) DEFAULT NULL,
  `accountNo` varchar(20) DEFAULT NULL,
  `bank` varchar(32) DEFAULT NULL,
  `insuredName` varchar(20) DEFAULT NULL,
  `insuredIdentifyNumber` varchar(20) DEFAULT NULL,
  `insuredIdentifyType` varchar(2) DEFAULT NULL,
  `insuredIdentifyPeriod` varchar(20) DEFAULT NULL,
  `insuredBirthDay` varchar(12) DEFAULT NULL,
  `insuredAge` varchar(2) DEFAULT NULL,
  `insuredSex` varchar(2) DEFAULT NULL,
  `appliIdentity` varchar(2) DEFAULT NULL,
  `insuredAddress` varchar(32) DEFAULT NULL,
  `postCode` varchar(8) DEFAULT NULL,
  `insuredPhoneNumber` varchar(20) DEFAULT NULL,
  `occupationCode` varchar(10) DEFAULT NULL,
  `insuredMobile` varchar(12) DEFAULT NULL,
  `insuredEmail` varchar(32) DEFAULT NULL,
  `serialNo` varchar(2) DEFAULT NULL,
  `clauseCode` varchar(10) DEFAULT NULL,
  `clausesName` varchar(32) DEFAULT NULL,
  `clausesContext` varchar(255) DEFAULT NULL,
  `travelGroupNo` varchar(2) DEFAULT NULL,
  `travelCountryCode` varchar(5) DEFAULT NULL,
  `travelCountryName` varchar(32) DEFAULT NULL,
  `travelLine` varchar(10) DEFAULT NULL,
  `ePolicyURL` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `ind_pro_no` (`proposalNo`),
  UNIQUE KEY `ind_trade_no` (`tradeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=552 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecfm_hd`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecfm_hd`;
CREATE TABLE `dt_insurecfm_hd` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `chnlNo` varchar(4) DEFAULT NULL COMMENT '渠道编号',
  `tranCode` varchar(255) DEFAULT NULL,
  `documentId` varchar(10) DEFAULT NULL,
  `profileRequest` varchar(5) DEFAULT NULL,
  `func` varchar(20) DEFAULT NULL,
  `src` varchar(10) DEFAULT NULL,
  `target` varchar(10) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=539 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecfm_itm`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecfm_itm`;
CREATE TABLE `dt_insurecfm_itm` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `headId` int(8) DEFAULT NULL,
  `tradeNo` varchar(32) DEFAULT NULL,
  `orderNo` varchar(20) NOT NULL COMMENT '订单号',
  `exopenId` varchar(20) DEFAULT NULL,
  `bankSeqNo` varchar(30) DEFAULT NULL COMMENT '支付流水号',
  `bankTradeDate` datetime DEFAULT NULL COMMENT '银行到账日期',
  `payType` varchar(2) DEFAULT NULL COMMENT '支付类型',
  `payment` varchar(10) DEFAULT NULL COMMENT '支付金额',
  `businessId` varchar(20) DEFAULT NULL,
  `agencyNo` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `ind_orderNo` (`orderNo`)
) ENGINE=InnoDB AUTO_INCREMENT=541 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecnla_hd`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecnla_hd`;
CREATE TABLE `dt_insurecnla_hd` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `chnlNo` varchar(4) DEFAULT NULL COMMENT '渠道编号',
  `tranCode` varchar(255) DEFAULT NULL,
  `documentId` varchar(10) DEFAULT NULL,
  `profileRequest` varchar(5) DEFAULT NULL,
  `func` varchar(20) DEFAULT NULL,
  `src` varchar(10) DEFAULT NULL,
  `target` varchar(10) DEFAULT NULL,
  `businessId` varchar(20) NOT NULL,
  `agencyNo` varchar(20) DEFAULT NULL,
  `applyDate` varchar(15) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecnla_itm`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecnla_itm`;
CREATE TABLE `dt_insurecnla_itm` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `headId` int(8) DEFAULT NULL,
  `policyNo` varchar(30) NOT NULL,
  `createTime` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecnlc_fb`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecnlc_fb`;
CREATE TABLE `dt_insurecnlc_fb` (
  `Id` int(8) NOT NULL AUTO_INCREMENT,
  `tradeNo` varchar(32) DEFAULT NULL,
  `responseCode` varchar(8) DEFAULT NULL,
  `responseMessage` varchar(255) DEFAULT NULL,
  `endorseNo` varchar(32) DEFAULT NULL,
  `endorsePrintNo` varchar(32) DEFAULT NULL,
  `chgPremium` varchar(8) DEFAULT NULL,
  `endorseText` varchar(1024) DEFAULT NULL,
  `printNo` varchar(22) DEFAULT NULL,
  `proposalNo` varchar(30) DEFAULT NULL,
  `policyNo` varchar(30) NOT NULL,
  `startDate` varchar(12) DEFAULT NULL,
  `endDate` varchar(12) DEFAULT NULL,
  `amount` varchar(8) DEFAULT NULL,
  `premium` varchar(8) DEFAULT NULL,
  `comCode` varchar(10) DEFAULT NULL,
  `comName` varchar(32) DEFAULT NULL,
  `comAddress` varchar(64) DEFAULT NULL,
  `comPostCode` varchar(8) DEFAULT NULL,
  `comPhoneNumber` varchar(18) DEFAULT NULL,
  `handler` varchar(20) DEFAULT NULL,
  `salesName` varchar(64) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecnlc_hd`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecnlc_hd`;
CREATE TABLE `dt_insurecnlc_hd` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `chnlNo` varchar(4) DEFAULT NULL COMMENT '渠道编号',
  `tranCode` varchar(255) DEFAULT NULL,
  `documentId` varchar(10) DEFAULT NULL,
  `profileRequest` varchar(5) DEFAULT NULL,
  `func` varchar(20) DEFAULT NULL,
  `src` varchar(10) DEFAULT NULL,
  `target` varchar(10) DEFAULT NULL,
  `businessId` varchar(20) NOT NULL,
  `agencyNo` varchar(20) DEFAULT NULL,
  `bankDealDate` varchar(15) DEFAULT NULL,
  `payType` varchar(2) DEFAULT NULL,
  `bankNo` varchar(10) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurecnlc_itm`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurecnlc_itm`;
CREATE TABLE `dt_insurecnlc_itm` (
  `Id` int(8) NOT NULL AUTO_INCREMENT,
  `headId` int(8) DEFAULT NULL,
  `policyNo` varchar(30) NOT NULL,
  `tradeNo` varchar(32) DEFAULT NULL,
  `createTime` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurereq_fb`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurereq_fb`;
CREATE TABLE `dt_insurereq_fb` (
  `Id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `tradeNo` varchar(32) NOT NULL,
  `applySeq` varchar(20) NOT NULL,
  `responseCode` varchar(8) DEFAULT NULL,
  `responseMessage` varchar(200) DEFAULT NULL,
  `productCode` varchar(20) DEFAULT NULL,
  `proposalNo` varchar(30) NOT NULL,
  `printNo` varchar(22) DEFAULT NULL,
  `startDate` varchar(20) DEFAULT NULL,
  `endDate` varchar(20) DEFAULT NULL,
  `amount` varchar(10) DEFAULT NULL,
  `premium` varchar(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '渠道交易流水号',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `idx_tradeNo` (`tradeNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2264 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurereq_hd`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurereq_hd`;
CREATE TABLE `dt_insurereq_hd` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `chnlNo` varchar(4) DEFAULT NULL COMMENT '渠道编号',
  `tranCode` varchar(10) DEFAULT NULL,
  `documentId` varchar(10) DEFAULT NULL,
  `profileRequest` varchar(5) DEFAULT NULL,
  `func` varchar(20) DEFAULT NULL,
  `src` varchar(10) DEFAULT NULL,
  `target` varchar(10) DEFAULT NULL,
  `orderNo` varchar(22) NOT NULL COMMENT '渠道订单编号',
  `businessId` varchar(20) DEFAULT NULL,
  `agencyNo` varchar(20) DEFAULT NULL,
  `statu` varchar(5) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `ind_orderNo` (`orderNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1692 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_insurereq_itm`
-- ----------------------------
DROP TABLE IF EXISTS `dt_insurereq_itm`;
CREATE TABLE `dt_insurereq_itm` (
  `Id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `headId` int(8) NOT NULL,
  `tradeNo` varchar(32) NOT NULL COMMENT '渠道交易流水号',
  `orderNo` varchar(22) NOT NULL,
  `applySeq` varchar(10) NOT NULL,
  `exopenId` varchar(20) DEFAULT NULL,
  `insurerName` varchar(150) DEFAULT NULL,
  `insurerGender` varchar(1) DEFAULT NULL,
  `insurerBirthday` date DEFAULT NULL,
  `insurerIdType` varchar(1) DEFAULT NULL,
  `insurerIdNo` varchar(20) DEFAULT NULL,
  `idActiveDate` date DEFAULT NULL,
  `idDisabledDate` date DEFAULT NULL,
  `insurerEmail` varchar(60) DEFAULT NULL,
  `insurerMobile` varchar(15) DEFAULT NULL,
  `insurantRelationship` varchar(1) DEFAULT NULL,
  `englishName` varchar(38) DEFAULT NULL,
  `phoneticizeLastname` varchar(38) DEFAULT NULL,
  `phoneticizeFirstname` varchar(38) DEFAULT NULL,
  `insurantName` varchar(150) DEFAULT NULL,
  `insurantGender` varchar(1) DEFAULT NULL,
  `insurantBirthday` date DEFAULT NULL,
  `insurantIdType` varchar(1) DEFAULT NULL,
  `insurantIdNo` varchar(20) DEFAULT NULL,
  `instidActiveDate` date DEFAULT NULL,
  `instidDisabledDate` date DEFAULT NULL,
  `instMobile` char(15) DEFAULT NULL,
  `insurerRelationship` char(1) DEFAULT NULL,
  `insurantEnglishName` varchar(38) DEFAULT NULL,
  `instPhoneticizeLastname` varchar(38) DEFAULT NULL,
  `instPhoneticizeFirstname` varchar(38) DEFAULT NULL,
  `linkManName` varchar(150) DEFAULT NULL,
  `linkManMobile` varchar(15) DEFAULT NULL,
  `linkManEmail` varchar(60) DEFAULT NULL,
  `productNo` varchar(20) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `effDate` datetime DEFAULT NULL,
  `matuDate` datetime DEFAULT NULL,
  `sumIns` varchar(10) DEFAULT NULL,
  `cfmFlag` varchar(1) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL COMMENT '1: 成功；0:失败；',
  `createTime` varchar(30) DEFAULT NULL,
  `updateTime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `ind_tradeNO` (`tradeNo`),
  UNIQUE KEY `ind_order_seq` (`orderNo`,`applySeq`)
) ENGINE=InnoDB AUTO_INCREMENT=2577 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_ipAddress`
-- ----------------------------
DROP TABLE IF EXISTS `dt_ipAddress`;
CREATE TABLE `dt_ipAddress` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `orgCode` varchar(10) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `isValid` varchar(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_pdSaleStrategy`
-- ----------------------------
DROP TABLE IF EXISTS `dt_pdSaleStrategy`;
CREATE TABLE `dt_pdSaleStrategy` (
  `Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `productId` varchar(20) DEFAULT NULL,
  `insureCode` varchar(10) DEFAULT NULL,
  `saleStrategy` varchar(2) DEFAULT NULL,
  `premium` decimal(5,0) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_pdSaleStrategy_ccic`
-- ----------------------------
DROP TABLE IF EXISTS `dt_pdSaleStrategy_ccic`;
CREATE TABLE `dt_pdSaleStrategy_ccic` (
  `Id` int(8) NOT NULL AUTO_INCREMENT,
  `productId` varchar(20) DEFAULT NULL,
  `clauseCode` varchar(10) DEFAULT NULL,
  `kindCode` varchar(10) DEFAULT NULL,
  `premium` varchar(10) DEFAULT NULL COMMENT '保费',
  `saleStrategy` varchar(5) DEFAULT NULL,
  `dateRangeLow` int(4) DEFAULT NULL,
  `dateRangeHigh` int(4) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1163 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_pdSaleStrategy_ccic_copy`
-- ----------------------------
DROP TABLE IF EXISTS `dt_pdSaleStrategy_ccic_copy`;
CREATE TABLE `dt_pdSaleStrategy_ccic_copy` (
  `Id` int(8) NOT NULL AUTO_INCREMENT,
  `productId` varchar(20) DEFAULT NULL,
  `clauseCode` varchar(10) DEFAULT NULL,
  `kindCode` varchar(10) DEFAULT NULL,
  `premium` varchar(10) DEFAULT NULL COMMENT '保费',
  `saleStrategy` varchar(5) DEFAULT NULL,
  `dateRangeLow` int(4) DEFAULT NULL,
  `dateRangeHigh` int(4) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1164 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_policy_fact`
-- ----------------------------
DROP TABLE IF EXISTS `dt_policy_fact`;
CREATE TABLE `dt_policy_fact` (
  `Id` int(8) NOT NULL AUTO_INCREMENT,
  `policyNo` varchar(32) NOT NULL,
  `startDate` varchar(20) DEFAULT NULL,
  `endDate` varchar(20) DEFAULT NULL,
  `amount` varchar(10) DEFAULT NULL,
  `premium` varchar(10) DEFAULT NULL,
  `productId` varchar(32) DEFAULT NULL,
  `channlCode` varchar(10) DEFAULT NULL,
  `insureCode` varchar(10) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `ind_policyNo` (`policyNo`,`channlCode`,`insureCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=668 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_product_ccic`
-- ----------------------------
DROP TABLE IF EXISTS `dt_product_ccic`;
CREATE TABLE `dt_product_ccic` (
  `Id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `productId` varchar(20) DEFAULT NULL COMMENT '产品ID',
  `insurePdId` varchar(20) DEFAULT NULL,
  `insureCode` varchar(10) DEFAULT NULL,
  `clauseCode` varchar(10) DEFAULT NULL COMMENT '条款代码',
  `kindCode` varchar(10) DEFAULT NULL COMMENT '责任代码',
  `amount` varchar(10) DEFAULT NULL COMMENT '保额，以万元为单位，仅存数值',
  `saleStrategy` varchar(2) DEFAULT NULL COMMENT '销售策略',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dt_product_fact`
-- ----------------------------
DROP TABLE IF EXISTS `dt_product_fact`;
CREATE TABLE `dt_product_fact` (
  `Id` int(8) NOT NULL,
  `productId` varchar(20) DEFAULT NULL COMMENT '产品id',
  `insureCode` varchar(10) DEFAULT NULL COMMENT '保险公司代码',
  `channlCode` varchar(10) DEFAULT NULL COMMENT '渠道方代码',
  `insurePdId` varchar(10) DEFAULT NULL COMMENT '保险公司内部产品id',
  `channlPdId` varchar(10) DEFAULT NULL COMMENT '渠道方内部产品id',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
