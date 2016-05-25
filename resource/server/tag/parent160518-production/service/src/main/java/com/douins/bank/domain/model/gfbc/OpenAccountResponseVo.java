package com.douins.bank.domain.model.gfbc;

//@XStreamAlias("SoEv")
public class OpenAccountResponseVo {
//	@XStreamAlias("Message")
	private Message message;
//	@XStreamAlias("RRQRes")
	private RRQResponse response;
	
	public Message getMessage() {
		return message;
	}
	public RRQResponse getResponse() {
		return response;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public void setResponse(RRQResponse response) {
		this.response = response;
	}
	
}
