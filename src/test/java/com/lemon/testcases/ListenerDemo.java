package com.lemon.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ListenerDemo {
    @BeforeMethod
    public void setup(){
        System.out.println("前置执行");
    }

    @Test
    public void demoTest(){
        //System.out.println("测试主体部分");
        Assert.assertEquals(1,2);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("后置执行");
    }
}
