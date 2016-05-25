package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class LinkManInfo {
    @XStreamAlias("linkmanname")
	private String linkManName;
    @XStreamAlias("linkmanmobile")
	private String linkmanmobile;
    @XStreamAlias("linkmanemail")
    private String linkManEmail;
    
    public String getLinkManName() {
        return linkManName;
    }
    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }
    public String getLinkmanmobile() {
        return linkmanmobile;
    }
    public void setLinkmanmobile(String linkmanmobile) {
        this.linkmanmobile = linkmanmobile;
    }
}
