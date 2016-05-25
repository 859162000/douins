/**
 * 
 */
package com.douins.statics.domain.model;

import java.io.Serializable;

import com.douins.common.util.ReadConfig;

/** 
* @ClassName: H 
* @Description: 静态 H5 页面 url
* @author G. F. 
* @date 2015年12月21日 下午3:35:19 
*  
*/
public class H5Url implements Serializable{
    private static final long serialVersionUID = 7235276311306400814L;

    private static final String url_product_moredetail = String.format("%s://%s/api/res/h5/moreDetail.html", ReadConfig.get("dy_url_domain"));

    private static H5Url h5Url = new H5Url();
    
    public static H5Url getInstance(){
        return h5Url;
    }

    public String getUrlProductMoredetail() {
        return url_product_moredetail;
    }
}
