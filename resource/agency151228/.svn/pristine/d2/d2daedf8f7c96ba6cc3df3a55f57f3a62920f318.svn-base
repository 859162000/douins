<?xml version="1.0" encoding="UTF-8"?>
<Values version="2.0">
    <array depth="${insure.records?size}" name="resultDocumentList" type="record">
        <#if insure.records?exists>
        <#list insure.records as record>
        <record javaclass="com.wm.util.Values">
            <value name="applyPolicyNo">${record.applyPolicyNo}</value>
            <value name="policyNo">${record.policyNo}</value>
            <#if record.responseCode == "1">
            <value name="resultFlag">Y</value>
            <value name="resultInfo">承保成功</value>
            <#else>
            <value name="resultFlag">N</value>
            <value name="resultInfo">承保失败:${record.errMsg}</value>
            </#if>
        </record>
        </#list>
        </#if>
    </array>
    <groupInfo>
        <orderNo>${insure.orderNo}</orderNo>
        <agencyNo>${insure.agencyNo}</agencyNo>
        <businessId>${insure.businessId}</businessId>
    </groupInfo>
    <#if insure.status == "1">
    <value name="processFlag">1</value>
    <value name="processMessage">承保处理成功</value>
    <#else>
     <value name="processFlag">0</value>
    <value name="processMessage">承保失败:${insure.errMsg}</value>
    </#if>
    <value name="interfaceId">17</value>
    <record javaclass="com.wm.util.Values" name="TN_params">
        <value name="$contentType">text/xml</value>
        <null name="$contentEncoding" />
    <value name="encoding">encoding="UTF-8"</value>
    </record>
</Values>
