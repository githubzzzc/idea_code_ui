package com.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SwipeTest {
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
        //下拉刷新-向下滑动
        //按住起始点->滑动到终止点位置上->手指抬起
        /*TouchAction touchAction = new TouchAction(androidDriver);
        //起始点(424,632)
        PointOption pointOptionA = PointOption.point(424,1000);
        //终止点(424,1000)
        PointOption pointOptionB = PointOption.point(424,632);
        //设置滑动时间-waitOptions类型的参数
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(1000));
        touchAction.press(pointOptionA).waitAction(waitOptions).moveTo(pointOptionB).release().perform();*/
        //swipeDown(1000);
        //连续向左滑动四次
/*        swipeLeft(1000);
        swipeLeft(1000);
        swipeLeft(1000);
        swipeLeft(1000);*/
/*        for(int i=0;i <4;i++){
            swipeLeft(10000);
        }*/
        //场景：滑动到页面底部
        driver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/button_go_login")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/et_mobile")).sendKeys("13323234545");
        driver.findElement(By.id("com.lemon.lemonban:id/et_password")).sendKeys("234545");
        driver.findElement(By.id("com.lemon.lemonban:id/btn_login")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"软件测试基础\")")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/first_level")).click();
        Thread.sleep(3000);
        //循环滑动
        while(true){
            //滑动前的页面源代码
            String beforeSource = driver.getPageSource();
            swipeUp(1000);
            Thread.sleep(1000);
            //什么时候跳出循环呢？
            //1、等待【全部加载完成】出来之后？ 缺点：（1）必须要保证此元素要立马出来 （2）有些页面没有这个提示
            //通过getPageSource进行判断
            /*if(driver.getPageSource().contains("全部加载完成")){
                break;
            }*/
            //2、getPageSource，通过滑动前后页面的pageSource是不是发生变化
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
