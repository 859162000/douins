/**
 * 
 */
package com.douins.agency.annotation;

import java.lang.annotation.*;

/** 
* @ClassName: IpAuth 
* @Description: 接口访问需要验证 IP 权限的注解
* @author G. F. 
* @date 2015年12月29日 下午2:18:49 
*  
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpAuth {
    /**
     * 接口访问是否需要验证 IP 的标识
     * @return
     */
    boolean ipAuth() default false;
}
