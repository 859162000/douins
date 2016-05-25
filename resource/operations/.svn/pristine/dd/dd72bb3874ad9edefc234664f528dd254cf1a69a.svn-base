package com.douins.agency.service.channel.qunar.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.douins.agency.service.channel.qunarfinance.service.ProcessService;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.douins.service.ChannelServiceIFC;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context.xml" })
public class lianQueryTest {
	
	@Resource(name = Constants.QUNARFN_SERVICE)
	private ChannelServiceIFC service;
	
	@Test
	public void test(){
		String data="<?xml version="+"1.0"+" encoding="+"UTF-8"+" standalone="+"yes"+"?>	<PackageList><Package><Header><Asyn>0</Asyn><Code>20002</Code><PartnerIdentifier>renrenbx</PartnerIdentifier><Time>2015-05-15 17:55:18</Time><UUID>03b412ea-d68a-49ce-89bf-4b762f812b24</UUID>"+
		"</Header><Request><policyNo>110706000081101A</policyNo> </Request></Package></PackageList>";
		System.out.println("data==="+data);
		service.doQuery(data);
	}
}
