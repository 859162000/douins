<@compress single_line=true>
<?xml version="1.0" encoding="gb2312"?>
<PackageList xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <Package>
        <Header>
        <RequestType>03</RequestType>
        <From>33</From>
        <SendTime>${policy.sendTime}</SendTime>
        </Header>
        <Request>
            <Payment>
                <OrderId>${policy.orderId}</OrderId>
                <PayTime>${policy.payTime}</PayTime>
                <PayMoney>${policy.payMoney?string("##0.00")}</PayMoney>
                <PayMode>79</PayMode>
            <AccountDate>${policy.payTime}</AccountDate>
            </Payment>
            <Proposal>
        <ProposalNo>${policy.proposalNo}</ProposalNo>
        <TotalPremium>${policy.totalPrem?string("##0.00")}</TotalPremium>
            </Proposal>
        </Request>
    </Package>
</PackageList>
</@compress>