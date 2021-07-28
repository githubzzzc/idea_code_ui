package com.lemon.data;

/**
 * 常量类
 */
public class Constants {
    //项目BaseUrl 测试环境-->预发布环境
    public static final String BASE_URL="http://8.129.91.152:8765";
    //登录页面地址
    public static final String LOGIN_URL = BASE_URL+"/Index/login.html";
    //首页页面地址
    public static final String INDEX_URL = BASE_URL+"/Index/index.html";
    //后台管理系统登录页面
    public static final String BACKSTAGE_URL = BASE_URL+"/Admin/Index/login.html";
    //正确的前台登录手机号码
    public static final String CORRECT_PHONE="13323234545";
    //正确的前台登录密码
    public static final String CORRECT_PWD="lemon123456";
    //配置测试浏览器
    public static final String TEST_BROWSER="chrome";
}
