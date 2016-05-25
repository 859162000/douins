/**
 * 
 */
package com.douins.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Element;

/** 
* @ClassName: Api 
* @Description: 接口标示
* @author G. F. 
* @date 2015年11月18日 下午3:47:40 
*  
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Api {
    
        /**
         * 是否需要登录才能访问的标识
         * @return
         */
        boolean token() default false;
}
