/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.mango.api.productAPI.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mango.api.productAPI.vo.ProductDetailResponse;
import com.mango.api.productAPI.vo.ProductRequest;
import com.mango.api.productAPI.vo.ProductResponse;
import com.mango.api.productAPI.vo.ProductVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.fortune.product.model.ProductDetail;
import com.mango.fortune.product.service.ProductDetailService;
import com.mango.fortune.product.service.ProductService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.framework.controller.BasicController;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月5日 下午4:00:24
 */
@Controller
@Scope("prototype")
@RequestMapping("/api/product")
public class ProductAPIController extends BasicController {
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDetailService productDetailService;

	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		ProductResponse productResp = new ProductResponse();
		try {
			ProductRequest productReq = JsonOnlineUtils.readJson2Entity(data,ProductRequest.class);
			if (productReq != null) {
				RequestTrans rt = productReq.getRequestTrans();
				transId = rt.getTransId();
				// 获取数据
				List<ProductVo> productList = productService.getProduct4Api(productReq);
				productResp.setProductList(productList);
				responseCode = ResponseCode.SUCCESS;
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			responseCode = ResponseCode.FAILED;
			logger.error("get product error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		productResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(productResp);
		return response;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getDetail", method = RequestMethod.POST)
	public String getDetail(HttpServletRequest request) {
		String data = request.getParameter("data");
		String transId = "";
		ResponseCode responseCode = ResponseCode.FAILED;
		ProductDetailResponse productDetailResp = new ProductDetailResponse();
		try {
			ProductRequest productReq = JsonOnlineUtils.readJson2Entity(data,ProductRequest.class);
			if (productReq != null) {
				RequestTrans rt = productReq.getRequestTrans();
				transId = rt.getTransId();
				// 获取数据
				ProductDetail pd = productDetailService.getByProductId(productReq.getProduct().getProductId());
				productDetailResp.setProductDetail(pd);
				responseCode = ResponseCode.SUCCESS;
			}else{
				responseCode = ResponseCode.DATAREAD_ERROR;
			}
		} catch (Exception e) {
			responseCode = ResponseCode.FAILED;
			logger.error("get product error", e);
		}
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
		productDetailResp.setResponseTrans(responseTrans);
		String response = JsonOnlineUtils.object2json(productDetailResp);
		return response;
	}
	
}
