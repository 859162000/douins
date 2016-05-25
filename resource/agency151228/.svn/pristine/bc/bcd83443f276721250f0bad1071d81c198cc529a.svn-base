/**
 * create by jjy 2016-05-06
 * 查询产品总销售金额的接口，专为douins的APP查询产品销售总金额
 */
package com.douins.agency.rest.ccic;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.agency.rest.BaseController;
import com.douins.agency.service.douins.service.IProductSalesService;

@Controller
@RequestMapping("/productValid")
public class ProductValidController extends BaseController{
	//private Logger logger = Logger.getLogger(InsuredController.class);
	@Resource
	IProductSalesService productSalesService;
	
	@ResponseBody
	@RequestMapping("/getSumSales")
	public String valid(HttpServletRequest request){
		String productCode = request.getParameter("productCode");
		String result = productSalesService.getProductSaleSum(productCode);
		return result;
	}
}
