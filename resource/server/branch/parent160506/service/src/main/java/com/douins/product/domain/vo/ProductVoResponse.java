/**
 * 
 */
package com.douins.product.domain.vo;

import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: ProductVoResponse 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月30日 下午1:42:20 
*  
*/
public class ProductVoResponse extends ResponseTransVo {
    private static final long serialVersionUID = -5839780441342293303L;

    private ProductVo productVo;

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }
    
}
