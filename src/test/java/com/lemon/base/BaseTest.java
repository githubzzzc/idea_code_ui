package com.lemon.base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    Logger logger = Logger.getLogger(BaseTest.class);
    //定义全局static driver
    public static WebDriver driver;
    /**
     * 统一浏览器封装
     * @param browserName 指定打开浏览器名
     */
    public void openBrowser(String browserName){
        if("chrome".equalsIgnoreCase(browserName)){
            //执行打开chrome的代码
            //System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
            logger.info("打开【chrome】浏览器");
            driver = new ChromeDriver();
            //return driver;
        }else if("firefox".equalsIgnoreCase(browserName)){
            //执行打开firefox的代码
            System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
            logger.info("打开【firefox】浏览器");
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
            logger.info("打开【IE】浏览器");
            driver = new InternetExplorerDriver(capabilities);
            //return driver;
        }else {
            logger.info("浏览器不支持，请确认你的浏览器名是否正确");
            //return null;
        }
    }

    /**
     * 访问网址的二次封装
     * @param url
     */
    public void toUrl(String url){
        driver.get(url);
        logger.info("打开【"+url+"】网址");
    }

    /**
     * 隐式等待二次封装
     * @param timeOut
     */
    public void setImplicitlyWait(int timeOut){
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        logger.info("设置全局隐式等待时间【"+timeOut+"】");
    }

    /**
     * 获取当前页面的URL地址
     * @return
     */
    public String getUrl(){
        String url =  driver.getCurrentUrl();
        logger.info("获取当前页面的URL地址【"+url+"】");
        return url;
    }

    /**
     * 关闭浏览器
     */
    public void closeBrowser(){
        driver.quit();
        logger.info("关闭测试浏览器");
    }

    /**
     * 最大化浏览器
     */
    public void maxBrowser(){
        driver.manage().window().maximize();
        logger.info("浏览器最大化");
    }

    public void refreshBrowser(){
        driver.navigate().refresh();
        logger.info("刷新浏览器");
    }

    /**
     * 生成File文件截图的封装
     * @param picPath 截图文件需要保存的路径
     */
    public static void getScreenshotAsFile(String picPath){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //1、file对象
        //2、字节数组
        File srcfile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File descFile = new File(picPath);
        try {
            FileUtils.copyFile(srcfile,descFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成字节数组截图的封装
     */
    public static byte[] getScreenshotAsByte(){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        //1、file对象
        //2、字节数组
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
}
