package com.fleet.pages;

import com.fleet.utilities.ConfigurationReader;
import com.fleet.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;


public class LoginPage extends BasePage{

    @FindBy(id = "prependedInput")//this line will initialize web element
    public WebElement userNameInput;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(css = "[class='alert alert-error']")
    public WebElement warningMessage;

    public LoginPage() {

        PageFactory.initElements(Driver.get(), this);
    }


    public void login(String userName, String password){
        userNameInput.sendKeys(userName);
        //Keys.ENTER to replace login click
        passwordInput.sendKeys(password, Keys.ENTER);
        Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        WebElement loaderMask = Driver.get().findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
    }



    public void login(String role) {
        String userName = "";
        String password = ConfigurationReader.getProperty("password");
        switch (role) {
            case "driver":
                userName = ConfigurationReader.getProperty("driver.username");
                break;
            case "store manager":
                userName = ConfigurationReader.getProperty("store.manager.username");
                break;
            case "sales manager":
                userName = ConfigurationReader.getProperty("sales.manager.username");
                break;
            default:
                throw new RuntimeException("Invalid role!");
        }
        login(userName, password);
    }

}