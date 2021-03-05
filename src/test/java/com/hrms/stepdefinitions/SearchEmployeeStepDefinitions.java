package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.DatabaseUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.List;

public class SearchEmployeeStepDefinitions extends CommonMethods {

    private List<String> uiJobTitles;
    private List<String> dbJobTitles;

    @When("user shall click on employee list button")
    public void user_shall_click_on_employee_list_button() {
        click(dashboardPage.employeeListButton);
    }

    @When("user shall fill-in {string}")
    public void user_shall_fill_in(String id) {
        sendText(employeeListPage.searchEmployeeID, id, Keys.ENTER);
    }

    @When("user shall click on search button")
    public void user_shall_click_on_search_button() {
        click(employeeListPage.searchButton);
    }

    @When("user shall fill-name Employee name as {string}")
    public void user_shall_fill_name_Employee_name_as(String employeeName) {
        doubleClick(employeeListPage.searchEmployeeName);
        sendText(employeeListPage.searchEmployeeName, employeeName);
    }

    @And("user shall retrieve all job titles from dropdown")
    public void userShallRetrieveAllJobTitlesFromDropdown() {
        uiJobTitles = storeDropdownValues(employeeListPage.searchJobTitle);
    }

    @When("user shall retrieve all job titles from database")
    public void userShallRetrieveAllJobTitlesFromDatabase() {
        dbJobTitles = DatabaseUtils.databaseListOfStrings("select distinct job_title from ohrm_job_title where job_title <> 'Sales&Marketing Manager' order by job_title;");
    }

    @Then("user shall verify the two lists match")
    public void userShallVerifyTheTwoListsMatch() {
        for (int i = 0; i < uiJobTitles.size(); i++) {
            Assert.assertEquals(uiJobTitles.get(i).trim(), (dbJobTitles.get(i).trim()));
        }
    }
}
