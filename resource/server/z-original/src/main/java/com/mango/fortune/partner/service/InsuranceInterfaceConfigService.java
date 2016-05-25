package com.mango.fortune.partner.service;

import com.mango.fortune.partner.model.InsuranceInterfaceConfig;
import com.mango.orm.DbOperateService;

import java.util.List;

public interface InsuranceInterfaceConfigService extends DbOperateService<InsuranceInterfaceConfig>{
    public List<InsuranceInterfaceConfig> getAll();
}