package com.lemon.pages;

import com.lemon.base.BasePage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InvestPage extends BasePage {
    //投资输入框
    By investInputBy = By.xpath("//input[contains(@class,'invest-unit-investinput')]");
    //投标按钮
    By investButtonBy = By.xpath("//button[text()='投标']");
    //投资成功提示
    By investSuccessBy = By.xpath("//div[@class='layui-layer-content']//div[@class='capital_font1 note']");
    //项目的可投金额元素
    By amountToBeInvestBy = MobileBy.xpath("//span[@class='mo_span4']");
    //关闭弹窗图片
    By closePopBy = By.xpath("//div[@class='layui-layer-content']/div/div[1]/img");

    public void clickInvestPop(){
        click(closePopBy,"投资页面_弹窗关闭按钮");
    }

    /**
     * 得到当前项目可投金额
     * @return
     */
    public double getAmountToBeInvest(){
        String amount = getText(amountToBeInvestBy,"投资页面_可投金额");
        //字符串转成整型？？因为项目的可投金额单位是万，所以需要转换
        return Double.parseDouble(amount)*10000;
    }

    public double getUserLeaveAmount(){
        String leaveAmount = getAttribute(investInputBy,"data-amount","投资页面_可用余额");
        //转成浮点类型
        return Double.parseDouble(leaveAmount);
    }

    /**
     * 获取投资成功元素文本信息
     * @return
     */
    public String getInvestSuccessTips(){
        return getText(investSuccessBy,"投资页面_投资成功提示");
    }

    /**
     * 投资的业务操作
     * @param investAmount 投资金额
     */
    public void invest(String investAmount){
        type(investInputBy,"投资页面_投资金额输入框",investAmount);
        click(investButtonBy,"投资页面_投资按钮");
    }

}
