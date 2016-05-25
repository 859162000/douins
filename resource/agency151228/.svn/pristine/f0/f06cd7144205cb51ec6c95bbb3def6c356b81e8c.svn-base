package com.douins.agency.service.channel.qunarfinance.domain.model;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("insuredinfo")
public class InsuredInfo {
	@XStreamAlias("insurantlist")
	private List <Insurant> insurantlist; 
	private String isapplicant;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((insurantlist == null) ? 0 : insurantlist.hashCode());
		result = prime * result
				+ ((isapplicant == null) ? 0 : isapplicant.hashCode());
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
		InsuredInfo other = (InsuredInfo) obj;
		if (insurantlist == null) {
			if (other.insurantlist != null)
				return false;
		} else if (!insurantlist.equals(other.insurantlist))
			return false;
		if (isapplicant == null) {
			if (other.isapplicant != null)
				return false;
		} else if (!isapplicant.equals(other.isapplicant))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InsuredInfo [insurantlist=" + insurantlist + ", isapplicant="
				+ isapplicant + "]";
	}
	public List<Insurant> getInsurantlist() {
		return insurantlist;
	}
	public String getIsapplicant() {
		return isapplicant;
	}
	public void setInsurantlist(List<Insurant> insurantlist) {
		this.insurantlist = insurantlist;
	}
	public void setIsapplicant(String isapplicant) {
		this.isapplicant = isapplicant;
	} 
	
	
	
}
