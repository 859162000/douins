<@compress single_line=true>
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<PackageList>
	<Package>
		<Header>
			<Asyn>${header.asyn}</Asyn>
			<Code>${header.code}</Code>
			<PartnerIdentifier>${header.partneridentifier}</PartnerIdentifier>
			<Time>${header.time}</Time>
			<UUID>${header.uuid}</UUID>
		</Header>
		<Request>
			<UnderwirtingList>
				<UnderwirtingItem>
					<ApplyInfo>
						<AgentNum>${request.getunderwritinglist().get(0).getApplyinfo().getAgentNum()}</AgentNum>
						<Applicant>
							<CellPhoneNumber>${request.getunderwritinglist().get(0).getApplyinfo().getApplicant().getCellPhoneNumber}</CellPhoneNumber>
							<Email>${request.getunderwritinglist().get(0).getApplyinfo().getApplicant().getEmail()}</Email>
							<ID>${request.getunderwritinglist().get(0).getApplyinfo().getApplicant().getId()}</ID>
							<IDType>${request.getunderwritinglist().get(0).getApplyinfo().getApplicant().getIdtype()}</IDType>
							<Name>${request.getunderwritinglist().get(0).getApplyinfo().getApplicant().getName()}</Name>
							<Sex>${request.getunderwritinglist().get(0).getApplyinfo().getApplicant().getSex()}</Sex>
						</Applicant>
						<ApplyDate${request.getunderwritinglist().get(0).getApplyinfo().getApplydate()}</ApplyDate>
						<ApplyType>${request.getunderwritinglist().get(0).getApplyinfo().getApplyType()}</ApplyType>
						<ChannelNum>${request.getunderwritinglist().get(0).getApplyinfo().getChannelNum()}</ChannelNum>
						<ChannelReginNum>${request.getunderwritinglist().get(0).getApplyinfo().getChannelReginNum()}</ChannelReginNum>
						<ChannelType>${request.getunderwritinglist().get(0).getApplyinfo().getChannelType()}</ChannelType>
						<EffectiveDate>${request.getunderwritinglist().get(0).getApplyinfo().getEffectiveDate()}</EffectiveDate>
						<InsuredInfo>
							<InsurantList>
								<Insurant>
									<CellPhoneNumber>${request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).getCellphonenumber()}</CellPhoneNumber>
									<Email>${request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).getEmail()}</Email>
									<ID>request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).getId()</ID>
									<IDType>request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).getIdtype()</IDType>
									<InsurantApplicantRelation>request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).getInsurantapplicantrelation()</InsurantApplicantRelation>
									<Name>request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).Name</Name>
									<Sex>request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getInsurantlist().get(0).Sex</Sex>
								</Insurant>
							</InsurantList>
							<IsApplicant>request.getunderwritinglist().get(0).getApplyinfo().getInsuredinfo().getIsapplicant()</IsApplicant>
						</InsuredInfo>
						<ProductList>
							<ProductInfo>
								<AfterDayEffective>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getAfterdayeffective()}</AfterDayEffective>
								<CalculationRule>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getCalculationrule()}</CalculationRule>
								<CoverPeriodType>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getCoverperiodtype()}</CoverPeriodType>
								<EffectiveDate>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getEffectivedate()}</EffectiveDate>
								<InsuranceCategory>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getInsurancecategory()}</InsuranceCategory>
								<InsuranceCoverage>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getInsurancecoverage()}</InsuranceCoverage>
								<InsuranceNum>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getInsurancenum()}</InsuranceNum>
								<InsurancePeriod>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getInsuranceperiod()}</InsurancePeriod>
								<IsPackage>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getIspackage()}</IsPackage>
								<PeriodPremium>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getPeriodpremium()}</PeriodPremium>
								<PremPeriodType>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getPremperiodtype()}</PremPeriodType>
								<ProductCode>${request.getunderwritinglist().get(0).getApplyinfo().getProductlist().get(0).getProductcode()}</ProductCode>
							</ProductInfo>
						</ProductList>
						<TotalPremium>${request.getunderwritinglist().get(0).getApplyinfo().getTotalpremium()}</TotalPremium>
					</ApplyInfo>
					<OrderInfo>
						<OrderId>${request.getunderwritinglist().get(0).getOrderinfo().getOrderid()}</OrderId>
					</OrderInfo>
					<OtherInfo>
						<ConIndProxyNO>${request.getunderwritinglist().get(0).getOtherinfo().getConindproxyno()}</ConIndProxyNO>
						<InsureNO>${request.getunderwritinglist().get(0).getOtherinfo().getInsureno()}</InsureNO>
						<Plat>${request.getunderwritinglist().get(0).getOtherinfo().getPlat()}</Plat>
					</OtherInfo>
				</UnderwirtingItem>
			</UnderwirtingList>
		</Request>
	</Package>
</PackageList>
</@compress>