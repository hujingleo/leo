package com.youxin.ymall.buslog;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusLogger {
	String msg();

	/**
	 * 所属模块
	 * 
	 * @return
	 */
	String module() default "";

	/**
	 * 所属功能
	 * 
	 * @return
	 */
	String func() default "";

}
