package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("PackageList")
public class PackageList {
	@XStreamImplicit(itemFieldName="Package")
	private List<Package> package1=Lists.newArrayList();

	public List<Package> getPackage1() {
		return package1;
	}

	public void setPackage1(List<Package> package1) {
		this.package1 = package1;
	}

	

	
	

}
