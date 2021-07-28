package com.test.day02;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumApi {
    public static void main(String[] args) throws IOException, InterruptedException {
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
        //定位到【题库】元素
        //WebElement webElement = driver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku"));
        //获取class属性值
        //System.out.println(webElement.getAttribute("className"));
        //获取resource-id属性值
        //System.out.println(webElement.getAttribute("resourceId"));
        //Thread.sleep(5000);
        //获取页面名
        //System.out.println(driver.currentActivity());
        //获取设备时间信息
        //System.out.println(driver.getDeviceTime());
        //获取设备DPI，注意不是分辨率
        //System.out.println(driver.getDisplayDensity());
        //获取automation name，默认为null，如果有指定automation name为uiautomator2就为对应的值
        //System.out.println(driver.getAutomationName());
        //获取设备横竖屏状态，有PORTRAIT(竖屏)与LANDSCAPE(横屏)
        //System.out.println(driver.getOrientation());

        //按键操作
        //driver.findElement(By.id("com.lemon.lemonban:id/navigation_tiku")).click();
        //driver.findElement(By.id("com.lemon.lemonban:id/button_go_login")).click();
        Thread.sleep(3000);
/*        //返回到上一级页面
        KeyEvent keyEvent = new KeyEvent();
        //携带哪一个按键操作
        keyEvent.withKey(AndroidKey.BACK);
        driver.pressKey(keyEvent);*/

        //截图
        //OutputType.FILE -->返回的是文件对象
        //OutputType.BYTES -->返回的是字节数组
        //OutputType.BASE64 -->返回的是BASE64编码之后的字符串
/*        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        //目标文件对象
        File destFile = new File("D:\\pic\\test"+System.currentTimeMillis()+".png");
        //需要把file对象保存到本地文件中
        FileUtils.copyFile(srcFile,destFile);*/

        //坐标点击-不推荐，有兼容性问题
        //生成TouchAction对象（跟触摸动作相关）
        //TouchAction touchAction = new TouchAction(driver);
        //把原始坐标（546，1552）转换为PointOpiton类型
        //PointOption pointOption = PointOption.point(546,1552);
        //touchAction.press(pointOption).release().perform();
    }
}
