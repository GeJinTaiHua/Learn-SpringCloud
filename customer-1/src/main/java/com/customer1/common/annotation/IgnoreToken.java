package com.customer1.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽略token验证
 * @author
 * @date 2018/11/16 15:16
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreToken {
	
	/**
	 * 
	* @Title: isCollectionDevice
	* @Description: 是否采集设备信息，false不采集，true采集
	* @return
	 */
	boolean isCollectionDevice() default false;
}
