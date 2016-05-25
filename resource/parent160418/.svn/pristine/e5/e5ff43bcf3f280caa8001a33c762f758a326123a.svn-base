package com.douins.policy.service.iml;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.policy.dao.CustomerDao;
import com.douins.policy.domain.model.Customer;
import com.douins.policy.service.CustomerService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransType;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class CustomerServiceImpl implements CustomerService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<Customer> baseDao;
//	
//	String mapperName =Customer.class.getName();
	
	@Inject
	private CustomerDao customerDao;
	
	@Override
	public ResponseCode addCustomer(Customer customer) throws Exception {
		ResponseCode responseCode=ResponseCode.FAILED;
		Customer para=new Customer();
		para.setUserId(customer.getUserId());
		para.setName(customer.getName());
		para.setSex(customer.getSex());
		para.setBirthday(customer.getBirthday());
		para.setCardType(customer.getCardType());
		para.setCardNo(customer.getCardNo());
		List<Customer> list=this.findByCondition(para);
		if(list!=null&&list.size()>0){
			responseCode=ResponseCode.CUSTOMEREXISTSERROR;
		}else{
			if(this.save(SystemConstant.OP_USER,customer)){
				responseCode=ResponseCode.SUCCESS;
			}else{
				responseCode=ResponseCode.FAILED;
			}
		}
		return responseCode;
	}
	
	@Override
	public ResponseCode delete(Customer customer) throws Exception{
		ResponseCode responseCode=ResponseCode.FAILED;
		List<Customer> list=this.findByCondition(customer);
		if(list!=null&&list.size()>0){
			if(this.delete(SystemConstant.OP_USER,customer)){
				responseCode=ResponseCode.SUCCESS;
			}else{
				responseCode=ResponseCode.FAILED;
			}
		}else{
			responseCode=ResponseCode.CUSTOMERNOTEXISTSERROR;
		}
		return responseCode;
	}
	
	@Override
	public ResponseCode modify(Customer customer,String transType) throws Exception {
		ResponseCode responseCode=ResponseCode.FAILED;
		if(transType.equals(TransType.CUSTOMERBASISCHANGE.getValue())){
			if (StringUtils.isBlank(customer.getName())
					|| StringUtils.isBlank(customer.getSex())
					|| StringUtils.isBlank(customer.getCardNo())
					|| StringUtils.isBlank(customer.getCardType())
					|| customer.getBirthday() == null) {
				responseCode = ResponseCode.DATAREAD_ERROR;
			}else{
				Customer c=new Customer();
				c.setUserId(customer.getUserId());
				c.setName(customer.getName());
				c.setSex(customer.getSex());
				c.setBirthday(customer.getBirthday());
				c.setCardType(customer.getCardType());
				c.setCardNo(customer.getCardNo());
				List<Customer> list=this.findByCondition(c);
				if(list!=null&&list.size()>0){
					responseCode=ResponseCode.CUSTOMEREXISTSERROR;
				}else{
					Customer cus=new Customer();
					cus.setUserId(customer.getUserId());
					cus.setCustomerId(customer.getCustomerId());
					List<Customer> qlist=this.findByCondition(cus);
					cus=qlist.get(0);
					//修改客户五项基本信息
					cus.setName(customer.getName());
					cus.setSex(customer.getSex());
					cus.setBirthday(customer.getBirthday());
					cus.setCardType(customer.getCardType());
					cus.setCardNo(customer.getCardNo());
					if(this.update(SystemConstant.OP_USER,cus)){
						responseCode=ResponseCode.SUCCESS;
					}else{
						responseCode=ResponseCode.FAILED;
					}
				}
			}
		}else if(transType.equals(TransType.CUSTOMERDETAILCHANGE.getValue())){
			Customer cus=new Customer();
			cus.setUserId(customer.getUserId());
			cus.setCustomerId(customer.getCustomerId());
			List<Customer> qlist=this.findByCondition(cus);
			
			//将客户基本信息设置为数据库中的信息
			customer.setName(qlist.get(0).getName());
			customer.setSex(qlist.get(0).getSex());
			customer.setBirthday(qlist.get(0).getBirthday());
			customer.setCardType(qlist.get(0).getCardType());
			customer.setCardNo(qlist.get(0).getCardNo());
			
			if(this.update(SystemConstant.OP_USER,customer)){
				responseCode=ResponseCode.SUCCESS;
			}else{
				responseCode=ResponseCode.FAILED;
			}
		}else{
			responseCode=ResponseCode.NORMAL1;
		}
		
		return responseCode;
	}

	@Override
	public List<Customer> findByCondition(Customer paramT) {
		//return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	    return customerDao.getList(paramT);
	}
	
	@Override
	public Page<Customer> getPage(Customer paramT, Page<Customer> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public Customer findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return customerDao.selectByPrimaryKey(paramString);
	}

	@Override
	public boolean save(String paramString, Customer paramT) throws DataBaseAccessException {
//		try {
//			SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    customerDao.insert(paramT);
	    return true;
	}

	@Override
	public boolean update(String paramString, Customer paramT) throws DataBaseAccessException {
//		try {
//			SaveEntityUtils.setUpdateForEntity(paramT, SystemConstant.OP_USER);
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    customerDao.updateByPrimaryKey(paramT);
	    return true;
	}

	@Override
	public boolean delete(String paramString, Customer paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getCustomerId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    customerDao.updateByPrimaryKey(paramT);
	    return true;
	}
}