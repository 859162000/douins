package com.douins.partner.service.iml;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.partner.dao.InsuranceInterfaceConfigDao;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;
import com.douins.partner.service.InsuranceInterfaceConfigService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("insuranceInterfaceConfigService")
public class InsuranceInterfaceConfigServiceImpl implements
		InsuranceInterfaceConfigService {
	
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
//	@Autowired
//	private BaseDao<InsuranceInterfaceConfig> baseDao;
//	
//	private String mapper = InsuranceInterfaceConfig.class.getName();
	
	@Inject
	private InsuranceInterfaceConfigDao configDao;
	
	@Override
	public boolean delete(String arg0, InsuranceInterfaceConfig arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InsuranceInterfaceConfig findByKey(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InsuranceInterfaceConfig> getPage(
			InsuranceInterfaceConfig arg0, Page<InsuranceInterfaceConfig> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String arg0, InsuranceInterfaceConfig arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String arg0, InsuranceInterfaceConfig arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<InsuranceInterfaceConfig> getAll() {
		//return baseDao.getList(mapper +"Mapper.selectAll", "");
	    return configDao.selectAll();
	}

}
