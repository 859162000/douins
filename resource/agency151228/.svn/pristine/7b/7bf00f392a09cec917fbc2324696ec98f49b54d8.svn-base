package com.douins.agency.service.douins.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.douins.agency.service.douins.dao.ProductInfoDao;
import com.douins.agency.service.douins.domain.model.ProductValid;
import com.douins.agency.service.douins.service.IProductSalesService;

@Service
public class ProductSalesService implements IProductSalesService {

	private static final Logger logger = Logger.getLogger(ProductSalesService.class);

	@Resource
	private ProductInfoDao productInfoDao;

	/**
	 * 传入订单号、订单总金额、请求编号 请求编号：10002为核保、20002为承保、30002为退保
	 */
	@Override
	public int saveProductSales(String orderId, String totalSales, String tradeCode) {
		ProductValid productValid = getProductSaleWithOrderId(orderId);

		if (productValid == null || productValid.getProductCode() == null || "".equals(productValid.getProductCode())) {
			logger.info("通过订单ID找不到核保的产品数据，这是不可能的！");
			return 0;
		}

		ProductValid productValid1 = getProductSaleWithProductCode(productValid.getProductCode());
		try {
			if (productValid1 == null) {
				productValid1 = new ProductValid();
				productValid1.setId(-1);
				productValid1.setProductCode(productValid.getProductCode());
			}
			String temp = productValid1.getTotalSales() == null ? "0" : productValid1.getTotalSales();
			if (totalSales != null && !"".equals(totalSales)) {
				// 因为利安承保和退保返回的金额是以分为单位，所以得到的金额要*0.01
				BigDecimal total = (new BigDecimal(totalSales)).multiply(new BigDecimal("0.01"));
				if ("20002".equals(tradeCode)) {// 承保用加法
					total = total.add(new BigDecimal(temp));
				} else if ("30002".equals(tradeCode)) {// 退保用减法
					total = total.subtract(new BigDecimal(temp));
				}
				productValid.setTotalSales(total.toString());
				return saveProductSales(productValid);
			}
		} catch (Exception e) {
			logger.info("产品销售额度转换有错！");
		}
		return 0;
	}

	@Override
	public int saveProductSales(ProductValid productValid) {
		/**
		 * 更新或插入数据
		 */
		if (productValid.getId() == -1) {
			return productInfoDao.saveProductSales(productValid);
		}
		return productInfoDao.updateProductSales(productValid);
	}

	@Override
	public ProductValid getProductSaleWithProductCode(String productCode) {
		return productInfoDao.getProductSaleWithProductCode(productCode);
	}

	@Override
	public ProductValid getProductSaleWithOrderId(String orderId) {
		return productInfoDao.getProductSaleWithOrderId(orderId);
	}

	@Override
	public String getProductTotalSales( String productCode) {
		//通过产品编号取回销售总额值
		String result = null;
		try{
		ProductValid productValid = productInfoDao.getProductSaleWithProductCode(productCode);
		if(productValid!=null)
			result = new BigDecimal(productValid.getTotalSales()).multiply(new BigDecimal("0.01")).toString();
		}catch(Exception e){
			logger.info("通过产品编号获取产品销售总额：在这里捕获异常，不能让它直接抛到外面！"+e);
		}
		return result;
	}
	
	@Override
	public String getProductSaleSum(String productCode) {
		//通过产品编号取回销售总额值
		String result = null;
		try{
		ProductValid productValid = productInfoDao.getProductSaleSum(productCode);
		if(productValid!=null)
			result = new BigDecimal(productValid.getTotalSales()).toString();
		}catch(Exception e){
			logger.info("获取所有产品销售总额：在这里捕获异常，不能让它直接抛到外面！"+e);
		}
		return result;
	}

	
	@Override
	public ProductValid getProductSaleWithPolicyNo(String policyNo) {
		// TODO 通过保单号取出承保的产品编号
		return null;
	}

	@Override
	public int saveProductSalesWithISS(String orderId, String totalSales) {
		logger.info("承保更新产品销售总额度！走你！！！！");
		int result = -1;
		try {
			// 通过订单号取出承保的产品编号,从核保数据中取出产品编号，取不到那不可能的
			ProductValid req = productInfoDao.getProductSaleWithISS(orderId);
			if (req == null || req.getProductCode() == null)
				return -1;
			logger.info("承保更新产品销售总额度：通过订单号拿到核保的数据了。产品编号："+req.getProductCode()+",金额："+req.getTotalSales()+"元！");
			// 通过产品编号查询产品销售总额表是否存在，存在就返回，不存在就返回null
			ProductValid productValid = productInfoDao.getProductSaleWithProductCode(req.getProductCode());
			// 统计产品销售总额，并保存数据（插入或更新）
			if (productValid == null) {
				productValid = new ProductValid();
				productValid.setProductCode(req.getProductCode());
				productValid.setTotalSales(req.getTotalSales());
				result = productInfoDao.saveProductSales(productValid);
				logger.info("承保更新产品销售总额度：产品销售总额没有该承保数据，插入数据。产品编号："+req.getProductCode()+",金额："+req.getTotalSales()+"元！");
			} else {
				BigDecimal total = new BigDecimal(productValid.getTotalSales());
				BigDecimal totalData = total.add(new BigDecimal(req.getTotalSales()));
				productValid.setTotalSales(totalData.toString());
				result = productInfoDao.updateProductSales(productValid);
				logger.info("承保更新产品销售总额度：产品销售总额存在该承保数据，更新数据。产品编号："+req.getProductCode()+",金额："+req.getTotalSales()+"元！");
			}
		} catch (Exception e) {
			logger.info("保存承保的数据到产品销售总额表中：在这里捕获异常，不能让它直接抛到外面，这样就不影响用户承保数据了"+e);
		}
		return result;
	}

	@Override
	public int saveProductSalesWithWD(String policyNo, String totalSales) {
		logger.info("退保更新产品销售总额度！走你！！！！");
		int result = -1;
		try{
			//  通过保单号取出承保的产品编号
			ProductValid req = productInfoDao.getProductSaleWithPolicyNo(policyNo);
			if (req == null || req.getProductCode() == null)
				return -1;
			logger.info("退保更新产品销售总额度：通过订单号拿到核保的数据了。产品编号："+req.getProductCode()+",金额："+req.getTotalSales());
			// 通过产品编号查询产品销售总额表是否存在，存在就返回，不存在就返回null
			ProductValid productValid = productInfoDao.getProductSaleWithProductCode(req.getProductCode());
			if(productValid==null){
				logger.info("保存退保的数据到产品销售总额表中：为什么产品销售总额表没有对应产品编号的数据，这里肯定有问题，因为在承保的时候必定插入数据的！");
				return -1;
			}
			BigDecimal total = new BigDecimal(productValid.getTotalSales());
			BigDecimal totalData = total.subtract(new BigDecimal(req.getTotalSales()));
			productValid.setTotalSales(totalData.toString());
			// 统计产品销售总额，并保存数据
			result = productInfoDao.updateProductSales(productValid);
			logger.info("退保更新产品销售总额度：产品销售总额存在该退保数据，更新数据。产品编号："+req.getProductCode()+",金额："+req.getTotalSales()+"元！");
		}catch (Exception e) {
			logger.info("保存退保的数据到产品销售总额表中：在这里捕获异常，不能让它直接抛到外面，这样就不影响用户承保数据了"+e);
		}
		return result;
	}

}
