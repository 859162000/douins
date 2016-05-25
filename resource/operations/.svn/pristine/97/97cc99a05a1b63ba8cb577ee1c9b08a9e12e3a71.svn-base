<?xml version="1.0" encoding="UTF-8"?>
<Values version="2.0">
  <array depth="${uw.records?size}" name="resultDocumentList" type="record">
    <#if uw.records?exists>
    <#list uw.records as record>
    <record javaclass="com.wm.util.Values">
      <value name="applySeq">${record.applySeq}</value>
      <value name="applyPolicyNo">${record.applyPolicyNo}</value>
      <value name="payPremium">${record.payPremium}</value>
      <#if record.responseCode == "1">
      <value name="resultFlag">Y</value>
      <value name="resultInfo">核保成功</value>
      <#else>
      <value name="resultFlag">N</value>
      <value name="resultInfo">核保失败:${record.errMsg}</value>
      </#if>
    </record>
    </#list>
    </#if>
  </array>
  <groupInfo>
    <orderNo>${uw.orderNo}</orderNo>
    <agencyNo>${uw.agencyNo}</agencyNo>
    <businessId>${uw.businessId}</businessId>
  </groupInfo>
  <#if uw.status == "1">
  <value name="processFlag">1</value>
  <value name="processMessage">核保全部通过</value>
  <#else>
   <value name="processFlag">0</value>
   <value name="processMessage">核保未通过或部分通过</value>
  </#if>
  <value name="interfaceId">16</value>
  <record javaclass="com.wm.util.Values" name="TN_params">
    <value name="$contentType">text/xml</value>
    <null name="$contentEncoding"/>
    <value name="encoding">encoding="UTF-8"</value>
  </record>
</Values>