package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("SDOROOT")
public class NyPayInfo extends NYTransRequest{
	
	    @XStreamAlias("BODY")
	    private Body2 body;
		
		public Body2 getBody() {
			return body;
		}
		public void setBody(Body2 body) {
			this.body = body;
		}
		@Override
		public <T> void setBody(T body) {
			this.body=(Body2) body;
			
		}

}
