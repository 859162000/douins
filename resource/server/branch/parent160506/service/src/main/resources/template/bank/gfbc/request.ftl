<?xml version="1.0" encoding="utf-8"  standalone="no"?>
<SoEv>
    <Message id="${tradeNo}">
        <#if tradeType == "RRQReq">
        <RRQReq id="RRQReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <channelSeq>${tradeNo}</channelSeq>
            <certType>01</certType>
            <certNo>${certNo}</certNo>
        </RRQReq>
        <#elseif tradeType == "TPReq">
        <TPReq id="TPReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <channelSeq>${tradeNo}</channelSeq>
            <channelDate>${tradeDate}</channelDate>
            <channelTime>${tradeTime}</channelTime>
            <recAccount>${vAccountNo}</recAccount>
            <accountType>1</accountType>
            <payAccount>${payAccount}</payAccount>
            <payAmount>${payAmount?string("##0.00")}</payAmount>
            <currencyType>01</currencyType>
            <explain/>
            <extension/>
        </TPReq>
        <#elseif tradeType == "BQReq">
        <BQReq id = "BQReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <vAccountNo>${vAccountNo}</vAccountNo>
            <extension/>
        </BQReq>
        <#elseif tradeType == "WDReq">
        <WDReq id="WDReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <channelSeq>${tradeNo}</channelSeq>
            <channelDate>${tradeDate}</channelDate>
            <channelTime>${tradeTime}</channelTime>
            <recAccount>${recAccount}</recAccount>
            <accountType>1</accountType>
            <payAccount>${payAccount}</payAccount>
            <payAmount>${payAmount?string("##0.00")}</payAmount>
            <settleType>1</settleType>
            <currencyType>01</currencyType>
            <explain/>
            <extension/>
        </WDReq>
        <#elseif tradeType == "SECReq">
        <SECReq id = "SECReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <channelSeq>${tradeNo}</channelSeq>
            <channelDate>${tradeDate}</channelDate>
            <channelTime>${tradeTime}</channelTime>
            <orderType>${orderType}</orderType>
            <orderNo>${orderNo}</orderNo>
            <productId/>
            <productName/>
            <accountNo>${vAccountNo}</accountNo>
            <merchantNo>${bAccountNo}</merchantNo>
            <transAmt>${transAmount}</transAmt>
            <busiDate>${busiDate}</busiDate>
            <explain/>
            <extension/>
        </SECReq>
        <#elseif tradeType == "BEPQReq">
        <BEPQReq id="BEPQReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <channelSeq>${tradeNo}</channelSeq>
            <channelDate>${date}</channelDate>
            <channelTime>${time}</channelTime>
            <tradeCode>${tradeCode}</tradeCode>
            <extension></extension>
        </BEPQReq>
        <#elseif tradeType == "TRQReq">
        <TRQReq id="TRQReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <channelSeq>${channelSeq}</channelSeq>
            <tradeCode>${tradeCode}</tradeCode>
            <extension></extension>
        </TRQReq>
        <#elseif tradeType == "SBLQReq">
        <SBLQReq id="SBLQReq">
            <version>1.0.1</version>
            <instId>955081180000001</instId>
            <certId>0001</certId>
            <certType>01</certType>
            <certNo>${certNo}</certNo>
            <currPage>1</currPage>
            <pageSize>10</pageSize>
            <extension></extension>
        </SBLQReq>
        </#if>
    </Message>
</SoEv>