<@compress single_line=true>
<?xml version="1.0" encoding="gbk"?>
<PackageList xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <Package>
        <Header>
            <RequestType>${header.transType}</RequestType>
            <From>${header.submitChannel}</From>
            <PostCode>${header.postCode}</PostCode>
            <AgentCode>${header.agentCode}</AgentCode>
            <AgentChannel>${header.agentChannel}</AgentChannel>
            <IsPackage>${header.isPackage}</IsPackage>
            <SendTime>${header.sendTime}</SendTime>
        </Header>
        <Request>
            <Order>
                <OrderId>${policy.orderId}</OrderId>
                <TotalPremium>${policy.totalPremium}</TotalPremium>
                <item>
                    <SkuRiskCode>${policyInfo.skuRiskcode}</SkuRiskCode>
                    <ProductCode>${policyInfo.productCode}</ProductCode>
                    <Premium>${policyInfo.sumPrem?string("##0.00")}</Premium>
                    <ActualPremium>${policyInfo.sumPrem?string("##0.00")}</ActualPremium>
                    <Amount>${policyInfo.sumPrem?string("##0.00")}</Amount>
                    <ApplyNum>${policyInfo.insuredNum}</ApplyNum>
                    <ChargeYear>0</ChargeYear>
                    <ChargeYearFlag>1</ChargeYearFlag>
                    <PaymentFreq>5</PaymentFreq>
                    <AutoAplIndi>${policyInfo.autoAplIndi}</AutoAplIndi>
                    <CoverageYear>0</CoverageYear>
                    <CoverageYearFlag>1</CoverageYearFlag>
                    <RenewalPayBank/>
                    <RenewalAccountName/>
                    <RenewalPayNo/>
                </item>
            </Order>
            <ApplyInfo>
                <Holder>
                    <CustomList>
                        <Custom key="HolderName">${holder.name}</Custom>
                        <Custom key="HolderCardType">1</Custom>
                        <Custom key="HolderCardNo">${holder.certiCode}</Custom>
                        <Custom key="HolderBirthday">${holder.birthday}</Custom>
                        <Custom key="HolderSex">${holder.gender}</Custom>
                        <Custom key="HolderEmail">${holder.email}</Custom>
                        <Custom key="HolderMobile">13472443051</Custom>
                        <Custom key="HolderAddress">${holder.address1}</Custom>
                        <Custom key="HolderZip">${holder.zipCode}</Custom>
                    </CustomList>
                </Holder>
                <InsuredInfo>
                    <InsuredList>
                        <Insured>
                            <CustomList>
                                <Custom key="InsuredRelation">${insured.ralationToPH}</Custom>
                                <Custom key="InsuredName">${insured.name}</Custom>
                                <Custom key="HolderCardType">1</Custom>
                                <Custom key="InsuredCardNo">${insured.certiCode}</Custom>
                                <Custom key="InsuredBirthday">${insured.birthday}</Custom>
                                <Custom key="InsuredSex">${insured.gender}</Custom>
                                <Custom key="InsuredMobile">13472443051</Custom>
                                <Custom key="InsuredAddress">${insured.address1}</Custom>
                                <Custom key="InsuredZip">${insured.postCode}</Custom>
                            </CustomList>
                        </Insured>
                    </InsuredList>
                </InsuredInfo>
                <BenefitInfo>
                    <IsLegal>${benifit.legalBenifit}</IsLegal>
                    <Benefit>
                    <#if  benifit.legalBenifit != "1">
                        <Name>${benifit.name}</Name>
                        <Gender>${benifit.gender}</Gender>
                        <CertiCode>${benifit.certiCode}</CertiCode>
                        <CertiType>1</CertiType>
                        <Birthday>${benifit.birthday}</Birthday>
                        <Designation>${benifit.designation}</Designation>
                        <ShareOrder>1</ShareOrder>
                        <ShareRate>1.00</ShareRate>
                    <#else>
                        <Name/>
                        <Gender/>
                        <CertiCode/>
                        <CertiType/>
                        <Birthday/>
                        <Designation/>
                        <ShareOrder/>
                        <ShareRate/>
                    </#if>
                    </Benefit>
                </BenefitInfo>
            </ApplyInfo>
        </Request>
    </Package>
</PackageList>
</@compress>