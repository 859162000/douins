package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SDOROOT")
public class CallBackRequest {
	   
	    @XStreamAlias("SYS_HEAD")
	    private SysHead sysHead;
	    @XStreamAlias("BODY")
	    private Body body;
		public SysHead getSysHead() {
			return sysHead;
		}
		public void setSysHead(SysHead sysHead) {
			this.sysHead = sysHead;
		}
		public Body getBody() {
			return body;
		}
		public void setBody(Body body) {
			this.body = body;
		}
	    
	    
	

}
