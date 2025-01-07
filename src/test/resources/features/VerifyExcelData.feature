@excel
Feature: Verify user data from Excel file

  Scenario: Verify user roles and active status from Excel
    Given I load the Excel file "TestData.xlsx" and sheet "Sheet1"
    When I read the data from the Excel file
    Then I verify the following data exists:
      | Username | Password | Role      | Active |
      | user1    | pass123  | Admin     | true   |
      | user2    | pass456  | Customer  | true   |
      | user3    | pass789  | Admin     | false  |
      | user4    | pass321  | Customer  | true   |
      | user5    | pass654  | Guest     | false  |
