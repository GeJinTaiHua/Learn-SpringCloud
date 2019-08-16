package com.customer1.common.util;

import com.basic.common.constants.ResultCodeConstants;
import com.basic.domain.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

public class VailUtil {

    public static final Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }


    public static <T> String getParamError(T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> sets = validator.validate(obj, groups);
        if (sets == null || sets.isEmpty()) {
            return null;
        }
        Iterator<ConstraintViolation<T>> it = sets.iterator();
        if (it.hasNext()) {
            return it.next().getMessage();
        }
        return null;
    }

    /**
     * @param obj
     * @param groups
     * @return
     * @Title: GenerateHttpResultParamError
     * @Description: 参数错误响应
     */
    public static <T> HttpResult<T> GenerateHttpResultParamError(Object obj, Class<?>... groups) {
        String msg = getParamError(obj, groups);
        if (StringUtils.isEmpty(msg)) {
            return null;
        }
        HttpResult<T> rs = new HttpResult<T>(msg, ResultCodeConstants.CODE_S0006, null);
        return rs;
    }

    /**
     * @param data
     * @return
     * @Title: GenerateHttpResultSuccess
     * @Description: 正常响应
     */
    public static <T> HttpResult<T> GenerateHttpResultSuccess(T data) {
        HttpResult<T> rs = new HttpResult<T>(ResultCodeConstants.getMsg(ResultCodeConstants.RESULT_CODE_SUCESS), ResultCodeConstants.RESULT_CODE_SUCESS, data);
        return rs;
    }

    /**
     * @param data
     * @return
     * @Title: GenerateHttpResultError
     * @Description: 服务器错误响应
     */
    public static <T> HttpResult<T> GenerateHttpResultError(T data) {
        HttpResult<T> rs = new HttpResult<T>(ResultCodeConstants.getMsg(ResultCodeConstants.CODE_S0001), ResultCodeConstants.CODE_S0001, data);
        return rs;
    }

    /**
     * @param msg
     * @param code
     * @param data
     * @return
     * @Title: GenerateHttpResultError
     * @Description: 其他响应
     */
    public static <T> HttpResult<T> GenerateHttpResultError(String msg, String code, T data) {
        HttpResult<T> rs = new HttpResult<T>(msg, code, data);
        return rs;
    }
}
