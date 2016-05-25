/**
 * 
 */
package com.douins.bank.domain.enums;

/** 
* @ClassName: NYResponseCode 
* @Description: nybc 的返回码
* @author G. F. 
* @date 2015年12月8日 上午9:47:06 
*  
*  ID = identity
*  ACC = account
*  PWD = password
*/
public enum AccStatusCode {
    SUCCESS("0000", "操作 (处理) 成功"),
    NO_BACK("-1", "交易没有返回结果"),
    UNKNOW_ERROR("9999", "未知错误，抛出异常"),
    VIST_ERROR("E90001", "IP 无权访问，或是业务未配置"),
    UNAUTHORIZED("E90007", "用户未授权"),
    SIGN_ERROR("E00003", "验签失败"),
    ACTIVE_CHECK_ERROR("E90002", "未查到激活用户"),
    // 身份核查接口返回的状态码
    VERIFY_ID_0("10", "身份验证通过"),
    VERIFY_ID_1("11", "身份验证失败"),
    // 资金账户开户接口返回的状态码
    REGIST_ACC_0("20", "新用户"),
    REGIST_ACC_1("21", "存在已激活"),
    REGIST_ACC_2("22", "存在未设密码"),
    REGIST_ACC_3("23", "存在未绑卡"),
    REGIST_ACC_4("24", "已设密码、已绑卡、未激活"),
    // 查询账户接口返回的状态码
    ACC_ACTIVE_FLAG_0("30", "激活"),
    ACC_ACTIVE_FLAG_1("31", "未激活"),
    ACC_CARD_NO("32", "未绑卡或绑卡失败"),
    ACC_CARD_YES("33", "已绑卡"),
    ACC_PWD_FLAG_0("40", "未设置"),
    ACC_PWD_FLAG_1("41", "已设置"),
    ACC_STOP_FLAG_0("50", "未止付"),
    ACC_STOP_FLAG_1("51", "已止付"),
    ;
    private String value;
    private String name;
    
    private AccStatusCode(String value, String name){
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 返回不带前缀的信息码
     * @return
     */
    public String getValueWithoutPefix(){
        return value.substring(1);
    }
    
    public static AccStatusCode getTypeByValue(String value){
        for(AccStatusCode type : AccStatusCode.values()){
            if(value.equals(type.getValue())){
                return type;
            }
        }
        
        return AccStatusCode.NO_BACK;
    }
    
    public static AccStatusCode getTypeByName(String name){
        for(AccStatusCode type : AccStatusCode.values()){
            if(name.equals(type.getName())){
                return type;
            }
        }
        
        return AccStatusCode.NO_BACK;
    }
    
    /**
     * 获取注册账户返回的信息枚举
     * @param value 银行返回的代码
     * @return
     */
    public static AccStatusCode getRegistStatus(String value){
        value = "2" + value;
        return getTypeByValue(value);
    }
    
    /**
     * 账户是否激活
     * @param value
     * @return
     */
    public static AccStatusCode getActivFlag(String value){
        value = "3" + value;
        return getTypeByValue(value);
    }
    
    /**
     * 支付密码是否设置
     * @param value
     * @return
     */
    public static AccStatusCode getPwdFlag(String value){
        value = "4" + value;
        return getTypeByValue(value);
    }
    
    /**
     * 止付／未止付
     * @param value
     * @return
     */
    public static AccStatusCode getStopFlag(String value){
        value = "5" + value;
        return getTypeByValue(value);
    }
    
    /**
     * 返回不带前缀的值
     * @return
     */
    public String getRealValue(){
        return value.substring(1);
    }
}
