package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SDOROOT")
public class QueryPayResultRequest extends NYTransRequest{
	
	@XStreamAlias("BODY")
    private QueryPayResultRequestBody body;

	public QueryPayResultRequestBody getBody() {
		return body;
	}

 @Override
    public <T> void setBody(T body) {
        this.body = (QueryPayResultRequestBody)body;
    }



}
