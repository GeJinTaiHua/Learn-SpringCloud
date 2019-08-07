package com.app.domain;

import com.basic.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 当前用户缓存信息
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfo extends BaseDomain {
    private static final long serialVersionUID = -7037378272269389297L;

    /**
     * 用户Uuid
     */
    private String userUuid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 登录时间
     */
    private Date loginDate;
}
