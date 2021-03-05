package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    @FindBy(css = "a#welcome")
    public WebElement welcomeButton;

    @FindBy(css = "a#aboutDisplayLink")
    public WebElement aboutButton;

    @FindBy(linkText = "Change Password")
    public WebElement changePasswordButton;

    @FindBy(linkText = "Logout")
    public WebElement logoutButton;

    @FindBy(css = "a#menu_admin_viewAdminModule")
    public WebElement adminButton;

    @FindBy(css = "a#menu_pim_viewPimModule")
    public WebElement pimButton;

    @FindBy(css = "a#menu_pim_Configuration")
    public WebElement configurationButton;

    @FindBy(css = "a#menu_pim_configurePim")
    public WebElement pimOptionalFieldsButton;

    @FindBy(css = "a#menu_pim_listCustomFields")
    public WebElement pimCustomFieldsButton;

    @FindBy(css = "a#menu_admin_pimCsvImport")
    public WebElement pimDataImportButton;

    @FindBy(css = "a#menu_pim_viewReportingMethods")
    public WebElement pimReportingMethodsButton;

    @FindBy(css = "a#menu_pim_viewTerminationReasons")
    public WebElement pimTerminationReasonsButton;

    @FindBy(css = "a#menu_pim_viewEmployeeList")
    public WebElement employeeListButton;

    @FindBy(css = "a#menu_pim_addEmployee")
    public WebElement addEmployeeButton;

    @FindBy(css = "a#menu_core_viewDefinedPredefinedReports")
    public WebElement pimReportsButton;

    @FindBy(css = "a#menu_leave_viewLeaveModule")
    public WebElement leaveButton;

    @FindBy(css = "a#menu_time_viewTimeModule")
    public WebElement timeButton;

    @FindBy(css = "a#menu_recruitment_viewRecruitmentModule")
    public WebElement recruitmentButton;

    @FindBy(css = "a#menu__Performance")
    public WebElement performanceButton;

    @FindBy(css = "a#menu_dashboard_index")
    public WebElement dashboardButton;

    @FindBy(css = "a#menu_directory_viewDirectory")
    public WebElement directoryButton;

    public DashboardPage() {
        PageFactory.initElements(CommonMethods.driver, this);
    }
}
