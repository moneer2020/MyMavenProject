package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigurationReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class AddEmployeeStepDefinitions extends CommonMethods {

    @When("user shall login with valid credentials")
    public void user_shall_login_with_valid_credentials() {
        loginPage.adminLogin(ConfigurationReader.getPropertyValue("username"),
                ConfigurationReader.getPropertyValue("password"));
    }

    @When("user shall see dashboard page")
    public void user_shall_see_dashboard_page() {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        Assert.assertTrue(currentUrl.contains("dashboard"));
    }

    @When("user shall hover over PIM button")
    public void user_shall_hover_over_PIM_button() {
        mouseHover(dashboardPage.pimButton);
    }

    @When("user shall click on add employee button")
    public void user_shall_click_on_add_employee_button() {
        click(dashboardPage.addEmployeeButton);
    }

    @When("user shall fill-in first name and last name")
    public void user_shall_fill_in_first_name_and_last_name() {
        addEmployeePage.enterFirstAndLastName("Tester", "McTasty");
    }

    @When("user shall save user information")
    public void user_shall_save_user_information() {
        click(addEmployeePage.saveButton);
    }

    @Then("user shall verify user has been created")
    public void user_shall_verify_user_has_been_created() {
        String actualText = personalDetailsPage.profileText.getText();
        String expectedText = "Tester McTasty";
        Assert.assertEquals(actualText, expectedText);
    }

    @When("user shall fill-in first name {string}, middle name {string}, and last name {string}")
    public void user_shall_fill_in_first_name_middle_name_and_last_name(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstNameTextBox, firstName);
        sendText(addEmployeePage.middleNameTextBox, middleName);
        sendText(addEmployeePage.lastNameTextBox, lastName);
    }

    @Then("user shall verify {string} has been created")
    public void user_shall_verify_has_been_created(String fullName) {
        String actualText = personalDetailsPage.profileText.getText();
        Assert.assertEquals(actualText, fullName);
    }

    @And("user shall create login with {string} as username and {string} as password")
    public void userShallCreateLoginWithAsUsernameAndAsPassword(String username, String password) {
        addEmployeePage.createLoginDetails(username, password);
    }


    @When("enter {string}, {string}, and {string}")
    public void enterAnd(String firstName, String middleName, String lastName) {
        addEmployeePage.enterFirstMiddleAndLastName(firstName, middleName, lastName);
    }

    @Then("user shall verify {string}, {string}, and {string} has been created")
    public void userShallVerifyAndHasBeenCreated(String firstName, String middleName, String lastName) {
        String fullName = firstName + " " + middleName + " " + lastName;
        String actualText = personalDetailsPage.profileText.getText();
        Assert.assertEquals(actualText, fullName);
    }

    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable employees) {
        List<Map<String, String>> employeeList = employees.asMaps();

        for (Map<String, String> employee : employeeList){
            String firstName = employee.get("FirstName");
            String middleName = employee.get("MiddleName");
            String lastName = employee.get("LastName");
            String employeeId = employee.get("EmployeeId");

            addEmployeePage.enterFirstMiddleAndLastName(firstName, middleName, lastName);
//            sendText(addEmployeePage.employeeIDTextBox, employeeId);
            click(addEmployeePage.saveButton);

            String actualText = personalDetailsPage.profileText.getText();
            String expectedText = firstName + " " + middleName + " " + lastName;
            Assert.assertEquals(actualText, expectedText);
            mouseHover(dashboardPage.pimButton);
            click(dashboardPage.addEmployeeButton);
        }
    }

    @When("add multiple employees from excel {string} sheet and verify they are added")
    public void add_multiple_employees_from_excel_sheet_and_verify_they_are_added(String sheetName) {
        List<Map<String, String>> excelData = ExcelReader.excelReaderToListMap(Constants.TESTDATA_FILEPATH, sheetName);

        for (Map<String,String> data:excelData){
            String firstName = data.get("First Name");
            String middleName = data.get("Middle Name");
            String lastName = data.get("Last Name");
            String photograph = data.get("Photograph");
            String username = data.get("Username");
            String password = data.get("Password");

            addEmployeePage.enterFirstMiddleAndLastName(firstName, middleName, lastName);
            sendText(addEmployeePage.photographTextBox, photograph);
            addEmployeePage.createLoginDetails(username, password);
            click(addEmployeePage.saveButton);

            String actualText = personalDetailsPage.profileText.getText();
            String expectedText = firstName + " " + middleName + " " + lastName;
            Assert.assertEquals(actualText, expectedText);

            mouseHover(dashboardPage.pimButton);
            click(dashboardPage.addEmployeeButton);
        }
    }
}
