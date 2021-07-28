package com.test.day04;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SmallProgramTest {
    static AndroidDriver driver;
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        //实例化配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //1、测试哪一台设备
        desiredCapabilities.setCapability("deviceName","08e7c5997d2a");
        //2、指定测试平台Android or IOS
        desiredCapabilities.setCapability("platformName","Android");
        //3、指定测试App（包名-App唯一身份标识）
        desiredCapabilities.setCapability("appPackage","com.tencent.mm");
        //4、App启动配置(App启动入口)
        desiredCapabilities.setCapability("appActivity","com.tencent.mm.ui.LauncherUI");
        //重点，特别注意！！！不清除掉微信的数据 noReset
        desiredCapabilities.setCapability("noReset",true);
        // 支持X5内核应用自动化配置
        desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
        // ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候,
        // 把com.tencent.mm:appbrand0的webview识别成com.tencent.mm的webview.
        // 所以为了避免这个问题，加上androidProcess: com.tencent.mm:appbrand0
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // 初始化会默认将chrome浏览器打开，需要将Browser置为空
        desiredCapabilities.setBrowserName("");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //把配置发送给到Appium服务器(Driver实例化)
        driver = new AndroidDriver(url,desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //等待主页加载完成
        Thread.sleep(12000);
        swipeDown(2000);
        driver.findElement(By.xpath("//*[contains(@text,'柠檬班软')]")).click();
        //等待小程序加载完成
        Thread.sleep(8000);
        //切换context
        //Set<String> allContexts = driver.getContextHandles();
        //微信webview和chromeDriver版本
        //要切换的context名字是由WEBVIEW_小程序进程名
        driver.context("WEBVIEW_com.tencent.mm:appbrand0");
        //进入到web页面定位元素，操作元素
        //大坑：微信小程序进程会对应有三个web窗口，我们需要切换到正确的那个
        switchWindow("腾讯课堂柠檬班软件测试");
        //正常定位web元素
        driver.findElement(By.xpath("//a[contains(text(),'课程')]")).click();
        driver.quit();
    }

    /**
     * 公共窗口切换方法
     * @param title 标题
     */
    public static void switchWindow(String title){
        Set<String> allHandles = driver.getWindowHandles();
        for (String handle:allHandles){
            //判断是不是对应的句柄（根据什么来判断）
            if(driver.getTitle().equals(title)){
                //符合，跳出循环
                break;
            }else {
                //切换窗口
                driver.switchTo().window(handle);
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

}