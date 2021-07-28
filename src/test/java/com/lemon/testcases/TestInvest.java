package com.lemon.testcases;

import com.lemon.base.Assetion;
import com.lemon.base.BaseTest;
import com.lemon.data.Constants;
import com.lemon.pages.IndexPage;
import com.lemon.pages.InvestPage;
import com.lemon.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestInvest extends BaseTest {
    String loanTitle;
    @BeforeMethod
    public void setup(){
        //投资的前置？
        //1、打开浏览器
        openBrowser("chrome");
        //2、设置全局5S隐式等待
        setImplicitlyWait(5);
        //3、最大化浏览器窗口
        maxBrowser();
        //2、准备一个可投标的项目
        loanTitle = addBid();
        //3、访问地址
        toUrl(Constants.LOGIN_URL);
        //4、投资人登录成功
        LoginPage loginPage = new LoginPage();
        loginPage.login(Constants.CORRECT_PHONE,Constants.CORRECT_PWD);
        //5、账户金额足够的
        //测试数据准备问题？
        //（1）、通过UI界面准备测试数据 速度比较慢 直观
        //（2）、接口调用方式 接口可用 速度快
        //（3）、数据库方式 业务逻辑 最推荐
    }
    @Test
    public void testInvestSuccess() throws InterruptedException {
        //1、点击抢投标
        IndexPage indexPage = new IndexPage();
        indexPage.clickBid(loanTitle);
        InvestPage investPage = new InvestPage();
        //获取项目当前的可投金额
        double beforeAmount = investPage.getAmountToBeInvest();
        //获取当前用户的可用余额
        double beforeLeaveAmount = investPage.getUserLeaveAmount();
        //2、投资页面-投资
        investPage.invest("200");
        //断言
        //1、根据投资成功文本信息断言
        Thread.sleep(1000);
        Assert.assertEquals(investPage.getInvestSuccessTips(),"投标成功！");
        //关闭弹窗
        investPage.clickInvestPop();
        //刷新网页
        refreshBrowser();
        //获取项目当前的可投金额
        double afterAmount = investPage.getAmountToBeInvest();
        //获取当前用户的可用余额
        double afterLeaveAmount = investPage.getUserLeaveAmount();
        //2、根据账户的余额的减少-200
        Assetion.myAssertEquals(beforeLeaveAmount-afterLeaveAmount,200.0);
        //3、根据项目可投金额的减少-200
        Assetion.myAssertEquals(beforeAmount-afterAmount,200.0);
    }
    @AfterMethod
    public void tearDown(){
        closeBrowser();
    }

    public String addBid()  {
        //访问后台页面的地址
        driver.get(Constants.BACKSTAGE_URL);
        //后台登录
        driver.findElement(By.name("admin_name")).sendKeys("lemon7");
        driver.findElement(By.name("admin_pwd")).sendKeys("lemonbest");
        driver.findElement(By.name("code")).sendKeys("hapi");
        driver.findElement(By.xpath("//button[text()='登陆后台']")).click();
        //后台首页-借款管理
        driver.findElement(By.xpath("//span[text()='借款管理']")).click();
        //加标(在iframe中，需要切换iframe)
        driver.switchTo().frame("mainFrame");
        driver.findElement(By.xpath("//span[text()='加标']")).click();
        driver.findElement(By.xpath("//td[text()='借款人:']/following-sibling::td/span/input[1]")).sendKeys("13323234444");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //按下方向下键
        driver.findElement(By.xpath("//td[text()='借款人:']/following-sibling::td/span/input[1]")).sendKeys(Keys.ARROW_DOWN);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //按下Enter键
        driver.findElement(By.xpath("//td[text()='借款人:']/following-sibling::td/span/input[1]")).sendKeys(Keys.ENTER);
        String loanTitle = "测试"+System.currentTimeMillis();
        driver.findElement(By.xpath("//td[text()='贷款标题:']/following-sibling::td/input")).sendKeys(loanTitle);
        driver.findElement(By.xpath("//td[text()='年利率利息:']/following-sibling::td/input")).sendKeys("10");
        driver.findElement(By.xpath("//td[text()='借款期限:']/following-sibling::td/input")).sendKeys("6");
        driver.findElement(By.xpath("//td[text()='借款额度:']/following-sibling::td/input")).sendKeys("1000000");
        driver.findElement(By.xpath("//td[text()='竞标期限:']/following-sibling::td/input")).sendKeys("5");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//span[text()='风控评测']")).click();
        driver.findElement(By.xpath("//td[text()='评估价值:']/following-sibling::td/input")).sendKeys("2000000");
        driver.findElement(By.xpath("//span[text()='项目录入']")).click();
        driver.findElement(By.xpath("//td[text()='籍贯:']/following-sibling::td/input")).sendKeys("湖南长沙");
        driver.findElement(By.xpath("//td[text()='职业:']/following-sibling::td/input")).sendKeys("测试工程师");
        driver.findElement(By.xpath("//td[text()='年龄:']/following-sibling::td/input")).sendKeys("20");
        driver.findElement(By.xpath("//span[text()='提交']")).click();
        //三次审核
        for (int i=0; i<3;i++){
            //点击选择对应项目
            //这里需要通过Thread.sleep等待，因为表格数据需要得到服务器的返回才能加载出来，但是表格元素已经存在，如果不等待会报错
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//div[text()='"+loanTitle+"']")).click();
            driver.findElement(By.xpath("//span[text()='审核']")).click();
            driver.findElement(By.xpath("//span[text()='审核通过']")).click();
        }
        return loanTitle;

    }
}
