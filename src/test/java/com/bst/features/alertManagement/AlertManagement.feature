@AlertManagement
Feature: Alert Management

  Scenario: Open the Alert Management Page
    Given Log in as Admin "admin" user
    And  Go to the alert management Page
    Then Alert Management title has been displayed