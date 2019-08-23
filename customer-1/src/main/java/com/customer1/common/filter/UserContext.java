package com.customer1.common.filter;

import org.springframework.stereotype.Component;

/**
 * 用户信息
 **/
@Component
public class UserContext {
    /**
     * 关联ID
     */
    public static final String CORRELATION_ID = "tmx-correlation-id";
    /**
     * 用户token
     */
    public static final String AUTH_TOKEN = "tmx-auth-token";
    /**
     * 用户uuid
     */
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    private String correlationId;
    private String authToken;
    private String userId;
    private String orgId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
