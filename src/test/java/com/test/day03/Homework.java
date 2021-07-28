package com.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Homework {
    static AndroidDriver driver;
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
        driver = new AndroidDriver(url,desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
        //等待首页加载完成
        Thread.sleep(5000);
        //场景：滑动到页面底部
        driver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/button_go_login")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/et_mobile")).sendKeys("13323234545");
        driver.findElement(By.id("com.lemon.lemonban:id/et_password")).sendKeys("234545");
        driver.findElement(By.id("com.lemon.lemonban:id/btn_login")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"软件测试基础\")")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/first_level")).click();
        Thread.sleep(3000);
        clickElementInList("软件测试基础--初级--第20套");
        //循环滑动
       /* while(true){
            //滑动前的页面源代码
            String beforeSource = driver.getPageSource();
            //判断对应元素是否在当前页面源码中存在(文本信息)
            if(beforeSource.contains("软件测试基础--初级--第20套")){
                // 点击对应元素
                driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"软件测试基础--初级--第20套\")")).click();
                //退出循环
                break;
            }
            swipeUp(1000);
            Thread.sleep(1000);
            //滑动之后的页面源代码
            String afterSource = driver.getPageSource();
            if(afterSource.equals(beforeSource)){
                //滑动到了页面的底部
                break;
            }
        }*/

    }

    /**
     * 滑动列表元素点击
     * @param text 列表元素文本信息
     */
    public static void clickElementInList(String text){
        //循环滑动
        while(true){
            //滑动前的页面源代码
            String beforeSource = driver.getPageSource();
            //判断对应元素是否在当前页面源码中存在(文本信息)
            if(beforeSource.contains(text)){
                // 点击对应元素
                driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+text+"\")")).click();
                //退出循环
                break;
            }
            swipeUp(1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //滑动之后的页面源代码
            String afterSource = driver.getPageSource();
            if(afterSource.equals(beforeSource)){
                //滑动到了页面的底部
                break;
            }
        }
    }

    /**
     * 向下滑动的通用封装
     * @param swipeTime 滑动时间
     */
    public static void swipeDown(long swipeTime){
        //约定滑动起始点与终止点的位置
        //滑动起始点：1/2宽度 1/4高度  滑动终止点：1/2宽度 3/4高度
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();
        TouchAction touchAction = new TouchAction(driver);
        //起始点(424,632)
        PointOption pointOptionA = PointOption.point(screenWidth/2,screenHeight/4);
        //终止点(424,1000)
        PointOption pointOptionB = PointOption.point(screenWidth/2,screenHeight*3/4);
        //设置滑动时间-waitOptions类型的参数
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(swipeTime));
        touchAction.press(pointOptionA).waitAction(waitOptions).moveTo(pointOptionB).release().perform();
    }

    /**
     * 向上滑动的通用封装
     * @param swipeTime 滑动时间
     */
    public static void swipeUp(long swipeTime){
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();
        TouchAction touchAction = new TouchAction(driver);
        PointOption pointOptionA = PointOption.point(screenWidth/2,screenHeight*3/4);
        PointOption pointOptionB = PointOption.point(screenWidth/2,screenHeight/4);
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(swipeTime));
        touchAction.press(pointOptionA).waitAction(waitOptions).moveTo(pointOptionB).release().perform();
    }

    /**
     * 向左滑动的通用封装
     * @param swipeTime 滑动时间
     */
    public static void swipeLeft(long swipeTime){
        //约定滑动起始点与终止点的位置
        //滑动起始点：1/2宽度 1/4高度  滑动终止点：1/2宽度 3/4高度
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();
        TouchAction touchAction = new TouchAction(driver);
        //起始点(424,632)
        PointOption pointOptionA = PointOption.point(screenWidth*3/4,screenHeight/2);
        //终止点(424,1000)
        PointOption pointOptionB = PointOption.point(screenWidth/4,screenHeight/2);
        //设置滑动时间-waitOptions类型的参数
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(swipeTime));
        touchAction.press(pointOptionA).waitAction(waitOptions).moveTo(pointOptionB).release().perform();
    }

    /**
     * 向右滑动的通用封装
     * @param swipeTime 滑动时间
     */
    public static void swipeRight(long swipeTime){
        //约定滑动起始点与终止点的位置
        //滑动起始点：1/2宽度 1/4高度  滑动终止点：1/2宽度 3/4高度
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();
        TouchAction touchAction = new TouchAction(driver);
        //起始点(424,632)
        PointOption pointOptionA = PointOption.point(screenWidth/4,screenHeight/2);
        //终止点(424,1000)
        PointOption pointOptionB = PointOption.point(screenWidth*3/4,screenHeight/2);
        //设置滑动时间-waitOptions类型的参数
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(swipeTime));
        touchAction.press(pointOptionA).waitAction(waitOptions).moveTo(pointOptionB).release().perform();
    }
}
