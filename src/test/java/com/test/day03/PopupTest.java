package com.test.day03;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PopupTest {
    static AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //实例化配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //1、测试哪一台设备
        desiredCapabilities.setCapability("deviceName","127.0.0.1:62001");
        //2、指定测试平台Android or IOS
        desiredCapabilities.setCapability("platformName","Android");
        //3、指定测试App（包名-App唯一身份标识）
        desiredCapabilities.setCapability("appPackage","tv.danmaku.bili");
        //4、App启动配置(App启动入口)
        desiredCapabilities.setCapability("appActivity","tv.danmaku.bili.ui.splash.SplashActivity");
        //noReset配置设置为true：不清除掉App数据
        desiredCapabilities.setCapability("noReset",true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //把配置发送给到Appium服务器(Driver实例化)
        driver = new AndroidDriver(url,desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        //点击【登录按钮】
        try {
            driver.findElement(By.id("tv.danmaku.bili:id/drawer_handler")).click();
        }catch (Exception e){
            //找到对应的元素就进入到弹窗的处理机制中来
            //找到弹窗
            driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"我知道了\")")).click();
            Thread.sleep(3000);
            //点完弹窗之后继续再来点击对应的元素
            driver.findElement(By.id("tv.danmaku.bili:id/drawer_handler")).click();
        }

        //作业：
        //实现滑动列表找到对应元素点击

    }

}
