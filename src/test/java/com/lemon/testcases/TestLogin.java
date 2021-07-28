package com.lemon.testcases;

import com.lemon.base.Assetion;
import com.lemon.base.BaseTest;
import com.lemon.data.Constants;
import com.lemon.pages.IndexPage;
import com.lemon.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestLogin extends BaseTest {
    @BeforeMethod
    public void setup(){
        //前置
        //1、打开浏览器
        openBrowser(Constants.TEST_BROWSER);
        //2、打开登录的URL地址
        toUrl(Constants.LOGIN_URL);
        //3、设置隐式等待5S
        setImplicitlyWait(5);
    }
    @Test
    public void testLoginSuccess(){
        //测试部分
        LoginPage loginPage = new LoginPage();
        loginPage.login(Constants.CORRECT_PHONE,Constants.CORRECT_PWD);
        //断言
        //(1)、根据退出按钮出现
        IndexPage indexPage = new IndexPage();
        //Assert.assertTrue(indexPage.isQuitVisible(driver));
        Assetion.myAssertTrue(indexPage.isQuitVisible());
        //(2)、用户昵称出现
        Assetion.myAssertTrue(indexPage.isMyAccountVisible());
        //(3)、页面的变化——url发生了变化
        String currentUrl = getUrl();
        Assetion.myAssertEquals(currentUrl,"http://8.129.91.152:8765/Index/inde");
    }

    @Test
    public void testLoginFailure1(){
        LoginPage loginPage = new LoginPage();
        loginPage.login("15859019266","123456");
        //断言
        //根据页面的提示信息
        Assetion.myAssertEquals(loginPage.getCenterTips(),"此账号没有经过授权，请联系管理员!");
    }


   @Test(dataProvider = "getLoginFailureDatas")
   public void testLoginFailure2(String phone,String pwd,String tips) throws InterruptedException {
       LoginPage loginPage = new LoginPage();
       loginPage.login(phone,pwd);
       Assetion.myAssertEquals(loginPage.getInputTips(),tips);
   }

    @DataProvider
    public Object[][] getLoginFailureDatas(){
        Object[][] datas ={{"","123456","请输入手机号X"},
                {"1332323454","123456","请输入正确的手机号X"},
                {"133232345454","123456","请输入正确的手机号X"},
                {"1332323454%","123456","请输入正确的手机号X"}};
        //读取Excel
        //读取数据库
        return datas;
    }

    @AfterMethod
    public void teardown(){
        //后置
        //6、关闭测试浏览器
        closeBrowser();
    }


}
