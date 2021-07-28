package com.test.day04;

import com.lemon.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

public class GetScreenshot {


    Logger logger = Logger.getLogger(BaseTest.class);
    //定义全局static driver
    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, IOException {
        //1、打开浏览器
        openBrowser("chrome");
        driver.get("https://www.baidu.com");
        Thread.sleep(2000);
        //driver.ge
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //1、file对象
        //2、字节数组
        File srcfile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File descFile = new File("D:\\test\\111.png");
        FileUtils.copyFile(srcfile,descFile);

    }
    /**
     * 统一浏览器封装
     * @param browserName 指定打开浏览器名
     */
    public static void openBrowser(String browserName){
        if("chrome".equalsIgnoreCase(browserName)){
            //执行打开chrome的代码
            //System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            //logger.info("打开【chrome】浏览器");
            driver = new ChromeDriver();
            //return driver;
        }else if("firefox".equalsIgnoreCase(browserName)){
            //执行打开firefox的代码
            System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
            //logger.info("打开【firefox】浏览器");
            driver=new FirefoxDriver();
            //return driver;
        }else if("ie".equalsIgnoreCase(browserName)){
            //执行打开ie的代码
            //取消IE安全设置（忽略IE的Protected Mode的设置）
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //忽略掉浏览器缩放设置问题
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            //表示让我们脚本使用对应的驱动程序iedriver.exe
            System.setProperty("webdriver.ie.driver","src\\test\\resources\\IEDriverServer.exe");
            //logger.info("打开【IE】浏览器");
            driver = new InternetExplorerDriver(capabilities);
            //return driver;
        }else {
            //logger.info("浏览器不支持，请确认你的浏览器名是否正确");
            //return null;
        }
    }
}
