/**
 * 
 */
package com.douins.account.domain.enums;


/** 
* @ClassName: TokenVerifyEnum 
* @Description: token 验证的枚举类
* @author G. F. 
* @date 2015年11月19日 上午9:09:49 
*  
*/
public enum TokenVerifyEnum {
    Normal(0, "正常"),
    Expired(1, "登录已过期，请重新登录"),
    NotExist(2, "请先登录");
    
    private int value;
    private String content;
    
    private TokenVerifyEnum(int val, String content){
        this.value = val;
        this.content = content;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
