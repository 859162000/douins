<?xml version="1.0" encoding="UTF-8"?>
<Values version="2.0">
<array depth="${apply.records?size}" name="resultDocumentList" type="record">
<#if apply.records?exists>
<#list apply.records as record>
<record javaclass="com.wm.util.Values">
<value name="resultFlag">${record.responseCode}</value>
<value name="revokeAmount">${record.revokeAmount}</value>
<#if record.responseCode == "01">
<value name="resultInfo">保单(${record.policyNo})契撤确认成功</value>
<#else>
<value name="resultInfo">保单(${record.policyNo})契撤确认失败:${record.errMsg}</value>
</#if>
<value name="policyNo">${record.policyNo}</value>
<value name="revokeDate">${record.revokeDate}</value>
</record>
</#list>
</#if>
</array>
<groupInfo>
<record>com.wm.util.Values</record>
</groupInfo>
<#if apply.status == "1">
<value name="processFlag">1</value>
<value name="processMessage">Process succeed</value>
<#else>
<value name="processFlag">0</value>
<value name="processMessage">Process failed</value>
</#if>
<value name="interfaceId">27</value>
<value name="abbsDebugInfo">null</value>
<record javaclass="com.wm.util.Values" name="TN_params">
<value name="$contentType">text/xml</value>
<null name="$contentEncoding" />
<value name="encoding">encoding="UTF-8"</value>
</record>
</Values>