package com.example.autowired.test.autow22.security;

import com.example.autowired.test.autow22.SpringApplicationContext;

public class SecurityConstants {
    //Security filters
    public static final long EXPIRATION_TIME = 300000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/test";
    //public static final String TOKEN_SECRET = "jf9i4jgu83nf10";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }

}
