Feature: Search Employee Function

  Background:
    When user shall login with valid credentials
    And user shall see dashboard page
    When user shall hover over PIM button
    And user shall click on employee list button

  @regression
  Scenario: Search for employee by ID
    When user shall fill-in "13209"
    And user shall click on search button

  @regression
  Scenario: Search for employee by name
    When user shall fill-name Employee name as "Luke Skywalker"
    And user shall click on search button

  @regression
  Scenario: View available job titles
    When user shall retrieve all job titles from database
    And user shall retrieve all job titles from dropdown
    Then user shall verify the two lists match








