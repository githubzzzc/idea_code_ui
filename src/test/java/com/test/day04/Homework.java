package com.test.day04;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Homework {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //实例化配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //1、测试哪一台设备
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //2、指定测试平台Android or IOS
        desiredCapabilities.setCapability("platformName","Android");
        //3、指定测试App（包名-App唯一身份标识）
        desiredCapabilities.setCapability("appPackage","com.lemon.lemonban");
        //4、App启动配置(App启动入口)
        desiredCapabilities.setCapability("appActivity","com.lemon.lemonban.activity.WelcomeActivity");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //把配置发送给到Appium服务器(Driver实例化)
        AndroidDriver androidDriver = new AndroidDriver(url,desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //进入到柠檬班社区页面中去
        androidDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"柠檬社区\")")).click();
        Thread.sleep(3000);
        //切换context（环境/上下文）原生页面环境&web页面的环境
        //获取到所有的context
        Set<String> allContexts = androidDriver.getContextHandles();
        System.out.println(allContexts);
        //切换context
        for (String contextName:allContexts){
            if(contextName.contains("WEBVIEW")){
                androidDriver.context(contextName);
            }
        }
        //定位到App内嵌的web页面元素(AndroidDriver-->WebDriver)
        androidDriver.findElement(By.xpath("//span[contains(text(),'注册')]")).click();
        androidDriver.findElement(By.xpath("//div[text()='已有帐号登录']")).click();

        androidDriver.findElement(By.id("nameOrEmail")).sendKeys("13323234545");
        androidDriver.findElement(By.id("userPassword")).sendKeys("123456");
        androidDriver.findElement(By.id("verifyLogin")).click();
        //Exception in thread "main" org.openqa.selenium.InvalidArgumentException: invalid argument: invalid locator
        //chromeDriver跟webview版本匹配版本 V2.40
    }
}
