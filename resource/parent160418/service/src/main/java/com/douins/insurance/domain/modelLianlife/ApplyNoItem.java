package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ApplyNoItem")
public class ApplyNoItem {
	@XStreamAlias("ApplySeq")
	private String applyseq;
	@XStreamAlias("SinglApplyNo")
	private String singlapplyno;
	public String getApplyseq() {
		return applyseq;
	}
	public void setApplyseq(String applyseq) {
		this.applyseq = applyseq;
	}
	public String getSinglapplyno() {
		return singlapplyno;
	}
	public void setSinglapplyno(String singlapplyno) {
		this.singlapplyno = singlapplyno;
	}
}
