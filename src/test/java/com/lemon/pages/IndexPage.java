package com.lemon.pages;

import com.lemon.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndexPage extends BasePage {
    //退出
    By quitBy = By.linkText("退出");
    //我的账户
    By myAccountBy = By.xpath("//a[contains(text(),'我的帐户')]");
    //抢投标
    //By bidButtonBy = By.xpath("//span[contains(text(),'"+loanTitle+"')]/parent::div/parent::a/following-sibling::div[1]//a");

    /**
     * 首页点击抢投标按钮
     * @param loanTitle 项目标题
     */
    public void clickBid(String loanTitle){
        By bidBy = By.xpath("//span[contains(text(),'"+loanTitle+"')]/parent::div/parent::a/following-sibling::div[1]//a");
        click(bidBy,"首页页面_项目标题");
    }

    /**
     * 判断【退出】元素是否可见
     * @return 可见状态
     */
    public boolean isQuitVisible(){
        //WebElement webElement1 = driver.findElement(quitBy);
        //return false;
        return isElemenVisible(quitBy,"首页页面_退出按钮");
        //return webElement1.isDisplayed();
    }

    /**
     * 判断【我的账户】元素是否可见
     * @return 可见状态
     */
    public boolean isMyAccountVisible(){
/*        WebElement webElement2 = driver.findElement(By.xpath("//a[contains(text(),'我的帐户')]"));
        return webElement2.isDisplayed();*/
        return isElemenVisible(myAccountBy,"主页页面_我的账户");
    }
}
