package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css = "input#txtUsername")
    public WebElement usernameTextBox;

    @FindBy(css = "input#txtPassword")
    public WebElement passwordTextBox;

    @FindBy(css = "input#btnLogin")
    public WebElement loginButton;

    @FindBy(css = "span#spanMessage")
    public WebElement invalidLoginMessage;

    public LoginPage(){
        PageFactory.initElements(CommonMethods.driver, this);
    }

    public void adminLogin(String username, String password){
        CommonMethods.sendText(usernameTextBox, username);
        CommonMethods.sendText(passwordTextBox, password);
        CommonMethods.click(loginButton);
    }


}
