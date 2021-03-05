package com.hrms.testbase;

import com.hrms.pages.*;
import com.hrms.utils.APICommonMethods;

public class PageInitializer extends BaseClass {

    public static LoginPage loginPage;
    public static DashboardPage dashboardPage;
    public static AddEmployeePage addEmployeePage;
    public static EmployeeListPage employeeListPage;
    public static PersonalDetailsPage personalDetailsPage;
    public static APICommonMethods apiCommonMethods;

    public static void initializePageObjects() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        addEmployeePage = new AddEmployeePage();
        employeeListPage = new EmployeeListPage();
        personalDetailsPage = new PersonalDetailsPage();
        apiCommonMethods = new APICommonMethods();
    }
}
