package com.nan.xarch.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface ResponseCode {
    String OK = "200";
    String SERVER_ERROR = "500";
    String LOGIN_ERROR = "400";
}
