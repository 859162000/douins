package com.douins.agency.service.channel.qunarfinance.domain.model;

public class ApplyNoItem {
	private String  applyseq;
	private String singlapplyno;
	public String getapplyseq() {
		return applyseq;
	}
	public void setapplyseq(String applyseq) {
		this.applyseq = applyseq;
	}
	public String getsinglapplyno() {
		return singlapplyno;
	}
	public void setsinglapplyno(String singlapplyno) {
		this.singlapplyno = singlapplyno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applyseq == null) ? 0 : applyseq.hashCode());
		result = prime * result
				+ ((singlapplyno == null) ? 0 : singlapplyno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplyNoItem other = (ApplyNoItem) obj;
		if (applyseq == null) {
			if (other.applyseq != null)
				return false;
		} else if (!applyseq.equals(other.applyseq))
			return false;
		if (singlapplyno == null) {
			if (other.singlapplyno != null)
				return false;
		} else if (!singlapplyno.equals(other.singlapplyno))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApplyNoItem [applyseq=" + applyseq + ", singlapplyno="
				+ singlapplyno + "]";
	}
	

}
