package com.seleniumTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestCases {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        // WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @Test
    public void testTitle() {
        String actualTitle=driver.getTitle();
        String expectedTitle="OrangeHRM";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testCopyRightsLink(){
        try {

            String expectedTitle="OrangeHRM HR Software | Free & Open Source HR Software | HRMS | HRIS | OrangeHRM";
          //  Thread.sleep(500);
            driver.findElement(By.cssSelector("a[href='http://www.orangehrm.com']")).click();
          //  Thread.sleep(500);
            Set<String> handles = driver.getWindowHandles();
            List<String> ls = new ArrayList<String>(handles);
            String  parent = ls.get(0);
            String child = ls.get(1);
            driver.switchTo().window(child);
          //  Thread.sleep(500);
            String actualTitle= driver.getTitle();
            System.out.println("actualTitle--> "+actualTitle);
         //   Thread.sleep(500);
            Assert.assertEquals(actualTitle, expectedTitle);
         //   //  Thread.sleep(1000);
        }catch(Exception e){e.getMessage();}
    }


    @Test
    public void testLogout(){

        String usr="Admin"; String pwd = "admin123";
        String actualText;
        try {
            /*******hover and click logout *********/
            driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(usr);
           // Thread.sleep(500);
            driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(pwd);
           // Thread.sleep(500);
            String loginBtn = "//button[contains(@class,\"orangehrm-login-button\")]";
            driver.findElement(By.xpath(loginBtn)).click();

            /******* click on profile picture space for logout *********/
           // Thread.sleep(1000);
            driver.findElement(By.xpath("//li[@class='oxd-userdropdown']")).click();
            String strLogoutXpath = "//a[normalize-space()='Logout']";


            /******* hover down list and click logout *********/
            Actions action = new Actions(driver);
            WebElement mainMenu = driver.findElement(By.xpath(strLogoutXpath));
          //  Thread.sleep(1000);
            action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath(strLogoutXpath))).click().build().perform();
           // Thread.sleep(1000);
            System.out.println(driver.getPageSource().contains("Login") ? "Logout successful!!": "Logout failed");

            /*******Assert that in login page *********/
            Assert.assertTrue(driver.getPageSource().contains("Login"));
        }catch(Exception e){e.getMessage();}
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }


}
