@currentTest
Feature: Login Functionality


  Scenario: Login with valid credentials
#    Given navigate to HRMS login page
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
#    And quit the browser

#  @regression
  Scenario: Login with invalid credentials
#    Given navigate to HRMS login page
    When enter invalid credentials
    And click on login button
    Then verify invalid credentials message
#    And quit the browser

#  @regression
  Scenario: Login with empty username
    When enter empty username and valid password
    And click on login button
    Then verify empty username message

#  @regression
  Scenario: Login with empty password
    When enter valid username and empty password
    And click on login button
    Then verify empty password message

