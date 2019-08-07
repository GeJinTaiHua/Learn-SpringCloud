package com.basic.common.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ViolationUtil {
	/**
	 * 通过violation验证已经配置过的实体类
	 * @param t 实体对象
	 * @param separator 实体对象
	 * @return 错误字符串，如果为null，那么表示验证成功
	 */
	public static <T> String validate(T t,String separator) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
		if(constraintViolations.size()<=0) {
			return null;
		}
		StringBuffer message = new StringBuffer("");
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			message.append(constraintViolation.getMessage());
			message.append(separator);
		}
		return message.toString();
	}
	/**
	 * 通过violation验证已经配置过的实体类
	 * @param t 实体对象
	 * @return 错误字符串，如果为null，那么表示验证成功
	 */
	public static <T> String validate(T t) {
		return validate(t,",");
	}
}
