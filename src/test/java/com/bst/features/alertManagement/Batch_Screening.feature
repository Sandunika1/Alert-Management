@batchEntity
Feature: batch screening Alert

  Background:
    Given Log in as Admin "admin" user
    And  Go to the alert management Page
#EL-T677,EL-680,EL-T1290, EL-T836, EL-T839,EL-T856
  Scenario Outline: Alert generation using batch file (SSB) with valid csv file
    When Upload "<batchFile>" batch file with "<confidenceLevel>"% of confidence
    Then Toast with "<numberOfNewAlerts>" alerts has been displayed
    And <numberOfNewAlerts> new alerts are displayed in the alerts table
    And <numberOfNewAlerts> alerts has been correctly added to statistics
    Examples:
      | numberOfNewAlerts | batchFile             | confidenceLevel |
      | 1                 | alertsPersonData.csv  | 40              |
      | 1                 | alertsCompanyData.csv | 40              |
