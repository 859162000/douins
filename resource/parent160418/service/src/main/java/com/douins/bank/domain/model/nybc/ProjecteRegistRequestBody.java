package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 5.25	支付项目信息登记接口
 * @author hou
 *
 */
public class ProjecteRegistRequestBody {
	@XStreamAlias("PROJ_NAME")
    private String name;
	@XStreamAlias("PROJ_DESC")
    private String desc;
	@XStreamAlias("PROJ_SIZE")
    private String size;
	@XStreamAlias("PROJ_CODE")
    private String code;
	@XStreamAlias("PROJ_STATUS")
    private String status;
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public String getSize() {
		return size;
	}
	public String getCode() {
		return code;
	}
	public String getStatus() {
		return status;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
		
}
