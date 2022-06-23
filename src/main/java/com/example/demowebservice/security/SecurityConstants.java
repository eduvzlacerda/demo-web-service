package com.example.demowebservice.security;

import com.example.demowebservice.utils.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 10000000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGNUP_URL = "/users";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return appProperties.getTokenSecret();
    }
}
