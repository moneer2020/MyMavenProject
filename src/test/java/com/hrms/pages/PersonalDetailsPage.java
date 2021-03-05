package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailsPage {

    @FindBy(xpath = "//div[@id = 'profile-pic']/h1")
    public WebElement profileText;

    @FindBy(css = "input#personal_txtEmpFirstName")
    public WebElement firstNameBox;

    @FindBy(css = "input#personal_txtEmpMiddleName")
    public WebElement middleNameBox;

    @FindBy(css = "input#personal_txtEmpLastName")
    public WebElement lastNameBox;

    @FindBy(css = "input#personal_txtEmployeeId")
    public WebElement employeeIDBox;

    @FindBy(css = "input#personal_txtOtherID")
    public WebElement otherIDBox;

    @FindBy(css = "input#personal_txtLicenNo")
    public WebElement licenseNumberBox;

    @FindBy(css = "input#personal_txtLicExpDate")
    public WebElement licenseExpirationBox;

    @FindBy(xpath = "(//img[@class = 'ui-datepicker-trigger'])[1]")
    public WebElement licenseExpirationCalendar;

    @FindBy(css = "input#personal_txtNICNo")
    public WebElement ssnBox;

    @FindBy(css = "input#personal_txtSINNo")
    public WebElement sinBoxBox;

    @FindBy(css = "input#personal_optGender_1")
    public WebElement maleRadioButton;

    @FindBy(css = "input#personal_optGender_2")
    public WebElement femaleRadioButton;

    @FindBy(css = "select#personal_cmbMarital")
    public WebElement martialStatusDropdown;

    @FindBy(css = "select#personal_cmbNation")
    public WebElement nationalityDropdown;

    @FindBy(css = "input#personal_DOB")
    public WebElement dateOfBirthBox;

    @FindBy(xpath = "(//img[@class = 'ui-datepicker-trigger'])[2]")
    public WebElement dateOfBirthCalendar;

    @FindBy(css = "input#personal_txtEmpNickName")
    public WebElement nickNameBox;

    @FindBy(css = "input#personal_txtMilitarySer")
    public WebElement militaryServiceBox;

    @FindBy(css = "input#personal_chkSmokeFlag")
    public WebElement smokerCheckBox;

    @FindBy(css = "input#btnSave")
    public WebElement editSaveButton;

    @FindBy(css = "input#btnAddAttachment")
    public WebElement addAttachmentButton;

    @FindBy(linkText = "Personal Details")
    public WebElement personalDetailsLink;

    @FindBy(linkText = "Contact Details")
    public WebElement contactDetailsLink;

    @FindBy(linkText = "Emergency Contacts")
    public WebElement emergencyContactsLink;

    @FindBy(linkText = "Dependents")
    public WebElement dependentsLink;

    @FindBy(linkText = "Immigration")
    public WebElement immigrationLink;

    @FindBy(linkText = "Job")
    public WebElement jobLink;

    @FindBy(linkText = "Salary")
    public WebElement salaryLink;

    @FindBy(linkText = "Tax Exemptions")
    public WebElement taxExemptionsLink;

    @FindBy(linkText = "Report-to")
    public WebElement reportToLink;

    @FindBy(linkText = "Qualifications")
    public WebElement qualificationsLink;

    @FindBy(linkText = "Memberships")
    public WebElement membershipsLink;

    public PersonalDetailsPage() {
        PageFactory.initElements(CommonMethods.driver, this);
    }
}
