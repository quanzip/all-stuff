package com.example.backend.utils;

import okhttp3.MediaType;

public class Constant {
    public static String CONFIGFILEPROPERTIES = "application";
    public static final String REQUEST_MAPPING_V1 = "/api/v1";
    public static final String COMMON_DATE_FORMAT = "dd/MM/yyyy";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FORM_URL_ENCODED = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    public static final String MESSAGE_CODE = "MESSAGE_CODE";
    public static final String MESSAGE_ERROR_VI = "MESSAGE_ERROR_VI";
    public static final String MESSAGE_ERROR_EN = "MESSAGE_ERROR_EN";
}
