package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    @FindBy(how = How.NAME,using = "username")
    WebElement username;
    @FindBy(name = "password") WebElement passwords;
    @FindBy(tagName = "button") WebElement loginButton;
    @FindBy(how = How.CLASS_NAME,using = "orangehrm-login-forgot-header") WebElement forgot;
    @FindBy(className = "orangehrm-forgot-password-button--reset") WebElement reset;
    @FindBy(css = "p.orangehrm-copyright:last-child a") WebElement companyPage;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void enterUsername(String name){
        username.sendKeys(name);
    }
    public void enterPassword(String password){
        passwords.sendKeys(password);
    }
    public void loginButton(){
        loginButton.click();
    }
    public void loginToOrangeHRM(String name,String password){
        enterUsername(name);
        enterPassword(password);
        loginButton();
    }
    public void clickOnForgotPassword(){
        forgot.click();
    }
    public void enterUsernameForgot(String name){
        username.sendKeys("Admin");
    }
    public void clickResetButton(){
        reset.submit();
    }
    public void clickCompany(){
        companyPage.click();
    }
    public void waitS(String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(url));
    }
}
