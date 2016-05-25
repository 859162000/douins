package com.douins.bank.domain.model.nybc;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class DopatArray {
	@XStreamImplicit(itemFieldName="")
	private List<Sdo> sdos;

	public List<Sdo> getSdos() {
		return sdos;
	}

	public void setSdos(List<Sdo> sdos) {
		this.sdos = sdos;
	}
	
	
	
	
	

}
