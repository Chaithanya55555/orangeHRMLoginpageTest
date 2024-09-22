package org.example;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
@Listeners(orangeHRM.class)
public class LoginFunctionality {
    WebDriver driver;
    LoginPage loginPage;
    String backgroundColor;
    String fontFamily;
    String fontSize;
    String fontWeight;
    String forgotPassword;
    String resetPasswordCompleted;
    ArrayList<String> currentlinks;
    String companyUrl;
    String parentWindow;
    List <String> windows;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("I am on orangeHRM website")
    public void navigateToUrl(){
        driver.manage().window().maximize();
       driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.waitS("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }
    @When("Enter username , password")
    public void enterUserNameAndPassword(){
        loginPage.loginToOrangeHRM("Admin","admin123");
    }
    @And("Click on login button")
    public void login(){
        loginPage.loginButton();
    }
    @Then("I redirected to home page")
    public void redirectAndVerify(){
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        loginPage.waitS(url);
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    @When("Enter invalid username and Click on login button")
    public void invalidUsername(){
        loginPage.enterUsername("Abcd");
        loginPage.loginButton();
    }
    @Then("Verify the error message for username")
    public void verifyErrorMsgForInvalidUsername(){
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/span")).getText(),"Required");
    }

    @When("Enter invalid password and Click on login button")
    public void invalidPassword(){
        loginPage.enterPassword("12345");
        loginPage.loginButton();
    }
    @Then("Verify the error message for password")
    public void verifyErrorMsgForInvalidPassword(){
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/span")).getText(),"Required");
    }

    @When("Enter valid username and invalid password and Click on login button")
    public void InvalidPasswordAndValidUsername(){
        loginPage.loginToOrangeHRM("Admin","123456");
        loginPage.loginButton();
    }
    @Then("Verify the error message for invalid password")
    public void errorMsgForInvalidPassword(){
        Assert.assertEquals(driver.findElement(By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > div > div.oxd-alert.oxd-alert--error > div.oxd-alert-content.oxd-alert-content--error > p")).getText(),"Invalid credentials");
    }

    @When("Enter invalid username and valid password and Click on login button")
    public void ValidPasswordAndInvalidUsername(){
        loginPage.loginToOrangeHRM("Abcdef","admin123");
        loginPage.loginButton();
    }
    @Then("Verify the error message for invalid username")
    public void errorMsgForInvalidUsername(){
        Assert.assertEquals(driver.findElement(By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > div > div.oxd-alert.oxd-alert--error > div.oxd-alert-content.oxd-alert-content--error > p")).getText(),"Invalid credentials");
    }

    @When("Get font family , font size , back-ground color and font weight")
    public void getCSSValues(){
        WebElement button = driver.findElement(By.tagName("button"));
        backgroundColor = button.getCssValue("background-color");
        fontFamily = button.getCssValue("font-family");
        fontSize = button.getCssValue("font-size");
        fontWeight = button.getCssValue("font-weight");
    }
    @Then("verify the CSS values")
    public void verifyCSSValues(){
        Assert.assertEquals(backgroundColor,"rgba(255, 123, 29, 1)");
        Assert.assertEquals(fontFamily,"\"Nunito Sans\", sans-serif");
        Assert.assertEquals(fontSize,"14px");
        Assert.assertEquals(fontWeight,"600");
    }

    @When("Click on forgot password link , enter username and click on reset password")
    public void clickOnForgot()  {
        loginPage.clickOnForgotPassword();
        loginPage.waitS("https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");
        forgotPassword = driver.getCurrentUrl();
        if(driver.findElement(By.name("username")).isEnabled()){
            loginPage.enterUsernameForgot("Admin");
        }
        loginPage.clickResetButton();
        loginPage.waitS("https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset");
        resetPasswordCompleted = driver.getCurrentUrl();
    }
    @Then("Redirect to forgot password webpage and successfully completed")
    public void verifyTheWebsite(){
        Assert.assertEquals(forgotPassword,"https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");
        Assert.assertEquals(resetPasswordCompleted,"https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset");
        Assert.assertEquals(driver.findElement(By.className("orangehrm-forgot-password-title")).getText(),"Reset Password link sent successfully");

    }

    @When("Click on every social media and verify it")
    public void clickOnSocialMedia() throws Exception{
        List <WebElement> socialMedia = driver.findElements(By.cssSelector("div.orangehrm-login-footer-sm a"));
        for (WebElement i : socialMedia){
            if(i.isDisplayed()){
                i.click();
            }
        }
        parentWindow = driver.getWindowHandle();
        windows = new ArrayList<>(driver.getWindowHandles());
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot, new File("C:\\Users\\HP\\Desktop\\orangeHRMSocialMedia.png"));
    }
    @Then("Redirect to respected web pages")
    public void verifyWebPages() throws Exception{
        ArrayList<String> expectedLinks = new ArrayList<>();
        expectedLinks.add("X");
        expectedLinks.add("OrangeHRM - World's Most Popular Opensource HRIS | Secaucus NJ | Facebook");
        expectedLinks.add("OrangeHRM | LinkedIn");
        expectedLinks.add("OrangeHRM Inc - YouTube");
        ArrayList<String> currentLinks = new ArrayList<>();
        for (int i = 0 ; i <= expectedLinks.size();i++) {
            if (!parentWindow.equals(windows.get(i))) {
                driver.switchTo().window(windows.get(i));
                Thread.sleep(3000);
                currentLinks.add(driver.getTitle());
            }
        }


           for (int j = 0 ; j < expectedLinks.size();j++){
               System.out.println(expectedLinks.contains(currentLinks.get(j)));
               Assert.assertTrue(expectedLinks.contains(currentLinks.get(j)));
           }

    }

    @When("Click on company link and verify")
    public void clickCompanyPage(){
        loginPage.clickCompany();
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String i:windows){
            if(!parentWindow.equals(windows)){
                driver.switchTo().window(i);
                companyUrl = driver.getCurrentUrl();
            }
        }
    }
    @Then("Redirect to company page")
    public void verifyCompanyPage(){
        Assert.assertEquals(companyUrl,"https://www.orangehrm.com/");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
