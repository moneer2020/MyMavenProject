package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPage {

    @FindBy(css = "input#empsearch_employee_name_empName")
    public WebElement searchEmployeeName;

    @FindBy(css = "input#empsearch_id")
    public WebElement searchEmployeeID;

    @FindBy(css = "select#empsearch_employee_status")
    public WebElement searchEmploymentStatus;

    @FindBy(css = "select#empsearch_termination")
    public WebElement searchInclude;

    @FindBy(css = "input#empsearch_supervisor_name")
    public WebElement searchSupervisor;

    @FindBy(css = "select#empsearch_job_title")
    public WebElement searchJobTitle;

    @FindBy(css = "select#empsearch_sub_unit")
    public WebElement searchSubUnit;

    @FindBy(css = "input#searchBtn")
    public WebElement searchButton;

    @FindBy(css = "input#resetBtn")
    public WebElement resetButton;

    @FindBy(css = "input#btnAdd")
    public WebElement addButton;

    @FindBy(css = "input#btnDelete")
    public WebElement deleteButton;

    @FindBy(xpath = "(//a[@class = 'tiptip'])[1]")
    public WebElement firstPageButton;

    @FindBy(xpath = "(//a[@class = 'tiptip'])[4]")
    public WebElement lastPageButton;

    @FindBy(xpath = "(//a[@class = 'tiptip'])[2]")
    public WebElement previousPageButton;

    @FindBy(xpath = "(//a[@class = 'tiptip'])[3]")
    public WebElement nextPageButton;

    public EmployeeListPage() {
        PageFactory.initElements(CommonMethods.driver, this);
    }
}
