package com.basic.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 数据检查工具类（比如字段空判断，集合空判断等）
 */
public class DataCheckUtil {


	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;

	}

	public static boolean isNotEmpty(String str) {
		return StringUtils.isNotEmpty(str);
	}

	public static boolean isLongEmpty(Long id) {
		if (id == null || id.longValue() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isIntEmpty(Integer id) {
		if (id == null || id <= 0) {
			return true;
		}
		return false;
	}


	public static boolean isEnumEmpty(String str) {
		if (isEmpty(str)) {
			return true;
		}
		return false;
	}

	public static boolean isBigDecimalEmptyAndZero(BigDecimal value){
		if (value == null || value.compareTo(BigDecimal.ZERO) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

	/**
	 *
	 * @Title: isInteger
	 * @Description: 判断金额是否为一个整数
	 * @param bigDecimal
	 * @return boolean
	 * @throws
	 */
	public static boolean isInteger(BigDecimal bigDecimal) {
		if (bigDecimal.compareTo(new BigDecimal(bigDecimal.longValue())) == 0) {
			return true;
		} else {
			return false;
		}
	}


}
