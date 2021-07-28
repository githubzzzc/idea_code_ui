package com.lemon.testcases;

import org.apache.log4j.Logger;

public class Log4jDemo {
    //log4j日志对象
    private static Logger logger = Logger.getLogger(Log4jDemo.class);
    public static void main(String[] args) {
        logger.info("我的信息是info级别");
        logger.debug("我的信息是debug级别");
        logger.warn("我的信息是warn级别");
        logger.error("我的信息是error级别");
        System.out.println("我的信息是info级别");
    }
}
