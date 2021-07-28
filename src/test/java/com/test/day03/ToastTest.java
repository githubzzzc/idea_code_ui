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

public class ToastTest {
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
        //driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
        //等待首页加载完成
        Thread.sleep(5000);
        driver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku")).click();
        driver.findElement(By.id("com.lemon.lemonban:id/button_go_login")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.lemon.lemonban:id/et_mobile")).sendKeys("");
        driver.findElement(By.id("com.lemon.lemonban:id/et_password")).sendKeys("");
        driver.findElement(By.id("com.lemon.lemonban:id/btn_login")).click();
        //获取toast元素 文本：手机号码或密码不能为空
        //xpath表达式获取
        //WebElement webElement = driver.findElement(By.xpath("//*[@text='手机号码或密码不能为空']"));
        //System.out.println(webElement.getText());
        //如果时通过显示等待获取toast元素的时候，那么条件只能够是：等待元素存在presenceOfElementLocated
        WebDriverWait wait = new WebDriverWait(driver,8);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='手机号码或密码不能为空']")));
        System.out.println(webElement.getText());

        driver.quit();
        //Original error: Could not proxy command to remote server. Original error: Error: read ECONNRESET
        //此错误提示原因：之前的Appium会话没有完全的关闭
    }

}
