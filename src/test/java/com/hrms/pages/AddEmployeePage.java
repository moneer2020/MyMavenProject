package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage {

    @FindBy(css = "input#firstName")
    public WebElement firstNameTextBox;

    @FindBy(css = "input#middleName")
    public WebElement middleNameTextBox;

    @FindBy(css = "input#lastName")
    public WebElement lastNameTextBox;

    @FindBy(css = "input#employeeId")
    public WebElement employeeIDTextBox;

    @FindBy(css = "input#photofile")
    public WebElement photographTextBox;

    @FindBy(css = "input#chkLogin")
    public WebElement createLoginDetailsCheckBox;

    @FindBy(css = "input#user_name")
    public WebElement usernameTextBox;

    @FindBy(css = "input#user_password")
    public WebElement passwordTextBox;

    @FindBy(css = "input#re_password")
    public WebElement confirmPasswordTextBox;

    @FindBy(css = "select#status")
    public WebElement statusDropdown;

    @FindBy(css = "input#btnSave")
    public WebElement saveButton;

    public void enterFirstAndLastName(String firstName, String lastName){
        CommonMethods.sendText(firstNameTextBox, firstName);
        CommonMethods.sendText(lastNameTextBox, lastName);
    }

    public void enterFirstMiddleAndLastName(String firstName, String middleName, String lastName){
        CommonMethods.sendText(firstNameTextBox, firstName);
        CommonMethods.sendText(middleNameTextBox, middleName);
        CommonMethods.sendText(lastNameTextBox, lastName);
    }

    public void createLoginDetails(String username, String password){
        CommonMethods.click(createLoginDetailsCheckBox);
        CommonMethods.sendText(usernameTextBox, username);
        CommonMethods.sendText(passwordTextBox, password);
        CommonMethods.sendText(confirmPasswordTextBox, password);
    }

    public AddEmployeePage(){
        PageFactory.initElements(CommonMethods.driver, this);
    }
}
