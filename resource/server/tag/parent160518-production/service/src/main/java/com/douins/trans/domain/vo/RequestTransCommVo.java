/**
 * 
 */
package com.douins.trans.domain.vo;

/** 
* @ClassName: RequestTransCommVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月10日 上午10:39:33 
*  
*/
public class RequestTransCommVo extends RequestTransVo {

    /**
     * 
     */
    private static final long serialVersionUID = -7805043866972451829L;
    private Param param;
    private String urlType;

    public Param getParam() {
        return param;
    }
    public void setParam(Param param) {
        this.param = param;
    }
    public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public class Param{
        private String params;
        private String urlType;

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

		public String getUrlType() {
			return urlType;
		}

		public void setUrlType(String urlType) {
			this.urlType = urlType;
		}
        
        
    }
}
