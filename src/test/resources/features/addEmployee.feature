Feature: Add Employee Functionality

  Background:
    When user shall login with valid credentials
    And user shall see dashboard page
    When user shall hover over PIM button
    And user shall click on add employee button

  @smoke
  Scenario: Add employee without login details
    When user shall fill-in first name and last name
    And user shall save user information
    Then user shall verify user has been created

  @smoke
  Scenario: Add employee with login details
    When user shall fill-in first name "Jennifer", middle name "Bomb", and last name "Lopez"
    And user shall create login with "Pascual" as username and "RabbiTholE2021$!" as password
    And user shall save user information
    Then user shall verify "Jennifer Bomb Lopez" has been created

  @regression
  Scenario Outline: Adding multiple employees without login details
    When enter "<FirstName>", "<MiddleName>", and "<LastName>"
    And user shall save user information
    Then user shall verify "<FirstName>", "<MiddleName>", and "<LastName>" has been created

    Examples:
      | FirstName | MiddleName | LastName |
      | Tom       | Cat        | Cruise   |
      | What      | Fox        | Say      |

  @regression
  Scenario: Adding multiple employees in one execution
    When add multiple employees and verify they are added successfully
      | FirstName | MiddleName | LastName | EmployeeID |
      | Tyler     | R          | Perry    | 784512     |
      | Keekee    | Doyou      | Loveme   | 202020     |

  @regression
  Scenario: Adding multiple employees from excel sheet
    When add multiple employees from excel "AddEmployees" sheet and verify they are added