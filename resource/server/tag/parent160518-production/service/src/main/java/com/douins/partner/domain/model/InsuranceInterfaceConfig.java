package com.douins.partner.domain.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

/**
* @ClassName: InsuranceInterfaceConfig 
* @Description: 保险公司接口配置
* @author 
* @date 2015年11月13日 下午1:33:42 
*
 */
public class InsuranceInterfaceConfig extends AbstractModel {
	private static final long serialVersionUID = -9178415446833899658L;

	private Long id;

	private String configId;

	private String insuranceId;

	private String configType;

	private String configService;

	private String interfaceUrl;

	private String transType;

	private String asyn;

	private String isvalid;

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigService() {
		return configService;
	}

	public void setConfigService(String configService) {
		this.configService = configService;
	}

	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getAsyn() {
		return asyn;
	}

	public void setAsyn(String asyn) {
		this.asyn = asyn;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	@Override
	public void initPrimaryKey() {
		this.setConfigId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}