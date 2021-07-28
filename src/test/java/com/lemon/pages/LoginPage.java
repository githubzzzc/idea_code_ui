package com.lemon.pages;

import com.lemon.base.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    //属性--元素定位信息（元素定位方式+表达式值）
    //手机号码输入框
    By phoneBy = By.name("phone");
    //密码输入框
    By passwordBy =By.name("password");
    //登录按钮
    By loginBy = By.xpath("//button[text()='登录']");
    //页面中间的提示信息
    By centerTipsBy = By.className("layui-layer-content");
    //输入框的提示信息
    By inputTipsBy = By.className("form-error-info");

    /**
     * 登录业务操作
     * @param phone 手机号码
     * @param pwd 密码
     */
    public void login(String phone,String pwd){
        type(phoneBy,"登录页面_手机号码输入框",phone);
        type(passwordBy,"登录页面_密码输入框",pwd);
        //driver.findElement(loginBy).click();
        click(loginBy,"登录页面_登录按钮");
    }


    /**
     * 获取页面的中间提示信息
     * @return
     */
    public String getCenterTips(){
        return getText(centerTipsBy,"登录页面_中间提示信息");
    }

    /**
     * 获取输入框的提示信息
     * @return
     */
    public String getInputTips(){
        return getText(inputTipsBy,"登陆页面_输入框的提示信息");
    }
}
