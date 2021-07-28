package com.test.day02;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ElementLocate {
    public static void main(String[] args) throws MalformedURLException {
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
        AndroidDriver driver = new AndroidDriver(url,desiredCapabilities);
        //设置全局的隐式等待
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //1、id定位（通过resource-id属性的）
        //点击【题库】
        //driver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku")).click();
        //2、text定位（根据text属性）
        //driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"题库\")")).click();
        //3、AccessbilityId定位（通过content-desc属性的）
        //driver.findElement(MobileBy.AccessibilityId("题库")).click();
        //4、xpath定位
        //driver.findElement(By.xpath("//android.widget.TextView[@text='题库']")).click();
    }

}
