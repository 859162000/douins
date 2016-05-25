/**
 * Copyright (c) 2015 www.sinorfc.com All Rights Reserved.  
 */
package com.douins.rest.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.impl.calendar.BaseCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.web.BaseController;
import com.douins.product.domain.model.ProductDetail;
import com.douins.product.domain.vo.ProductDetailResponse;
import com.douins.product.domain.vo.ProductRequest;
import com.douins.product.domain.vo.ProductResponse;
import com.douins.product.domain.vo.ProductVo;
import com.douins.product.domain.vo.ProductVoResponse;
import com.douins.product.service.ProductDetailService;
import com.douins.product.service.ProductService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

/**
 * @author xuhuiwang
 * @version 1.0, 2015年5月5日 下午4:00:24
 */
@Controller
@Scope("prototype")
@RequestMapping("/product")
public class ProductAPIController extends BaseController{
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDetailService productDetailService;

	/**
	 * 产品(保险)列表
	 * @param request
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public String get(HttpServletResponse response,@RequestParam(required=true) String data) {
		ProductResponse productResp = new ProductResponse();
		try {
			ProductRequest productReq = fromJson(data,ProductRequest.class);
			if (productReq != null) {
				transId = productReq.getRequestTrans().getTransId();
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
		return renderString(response,productResp);

	}
	
	/**
	 * 获取指定产品的详情信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String getProductDetail(HttpServletRequest request  ){
	    ResponseCode responseCode = ResponseCode.FAILED;
	    String transId = null;
	    ProductVoResponse productResp = new ProductVoResponse();
	    
	    try {
            String data = request.getParameter("data");
            ProductRequest productReq = JsonOnlineUtils.readJson2Entity(data,ProductRequest.class);
            if (productReq != null) {
                RequestTrans rt = productReq.getRequestTrans();
                transId = rt.getTransId();
                // 获取数据
                logger.info("id = " + productReq.getProduct().getProductId());
                ProductVo productVo = productService.getProductById(productReq.getProduct().getProductId());
                productResp.setProductVo(productVo);
                
                responseCode = ResponseCode.SUCCESS;
            }else{
                responseCode = ResponseCode.DATAREAD_ERROR;
            }
        } catch (Exception e) {
            logger.error("get product error", e);
        }
	    
	    ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
        productResp.setResponseTrans(responseTrans);
        String response = JsonOnlineUtils.object2json(productResp);
        
	    return response;
	}
	
	/**
	 * 保险介绍
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 产品推荐
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/recommend", method=RequestMethod.POST)
	public String getRecommend(HttpServletRequest request){
	    String data = request.getParameter("data");
	    logger.info("产品推荐：" + data);
        String transId = "";
        ResponseCode responseCode = ResponseCode.FAILED;
        ProductResponse productResp = new ProductResponse();
        try {
            ProductRequest productReq = JsonOnlineUtils.readJson2Entity(data,ProductRequest.class);
            if (productReq != null) {
                RequestTrans rt = productReq.getRequestTrans();
                transId = rt.getTransId();
                // 获取数据
                ProductVo product = productService.getRecommendProduct();
                productResp.setProductList(product);
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
        logger.info(response);
        return response;
	}
	
	@RequestMapping("/moredetail")
	public  String getMoreDetail(HttpServletRequest request){
	    return "res/h5/moreDetail.html";
	}
}
