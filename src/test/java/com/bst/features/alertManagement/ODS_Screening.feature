@searchEntity
Feature: ODS screening Alert

  Background:
    Given Log in as Admin "admin" user
    And  Go to the alert management Page
  @confidence
#Test case: EL-T1287,EL-T851
  Scenario Outline:  Organization alert generation for non mandatory fields
    When Search for entity type: "<type>", confidence: "<confidence>"%, id: "<id>", entity name: "<entityName>", country: "<country>",country Of Registration: "<countryOfRegistration>",address: "<address>"
    Then Search initiated toast has been displayed
    And Toast with "<numberOfNewAlerts>" alerts has been displayed
    And <numberOfNewAlerts> new alerts are displayed in the alerts table
    And <numberOfNewAlerts> alerts has been correctly added to statistics
    Examples:
      | numberOfNewAlerts | type         | confidence | id     | entityName      | country | countryOfRegistration | address         |
      | 1                 | organization | 40         | 625217 | Bank Melli Iran | Iran    | 08/05/1927            | 23 Nobel Avenue |

#EL-T1286,EL-T849
  Scenario Outline:  Person alert generation for non mandatory fields
    When Search for entity type: "<type>", confidence: "<confidence>"%, id: "<id>", entity name: "<entityName>", country: "<country>", birth date: "<birthDate>", gender: "<gender>", nationality: "<nationality>", jobTitle: "<jobTitle>", address: "<address>"
    Then Search initiated toast has been displayed
    And Toast with "<numberOfNewAlerts>" alerts has been displayed
    And <numberOfNewAlerts> new alerts are displayed in the alerts table
    And <numberOfNewAlerts> alerts has been correctly added to statistics
    Examples:
      | numberOfNewAlerts | type   | confidence | id     | entityName    | country   | birthDate  | gender | nationality | jobTitle                         | address |
      | 1                 | person | 40         | 136625 | Chi Kwong Law | Hong Kong | 01/11/1953 | Male   | China       | Secretary for Labour and Welfare | HKONG   |

    #EL-T1484
  Scenario Outline: Search person alert with partial name
    When Search for entity type: "<type>", confidence: "<confidence>"%, id: "<id>", entity name: "<entityName>", country: "<country>", birth date: "<birth date>", gender: "<gender>", nationality: "<nationality>", jobTitle: "<jobTitle>", address: "<address>"
    Then Search initiated toast has been displayed
    And Toast with "<numberOfNewAlerts>" alerts has been displayed
    And <numberOfNewAlerts> new alerts are displayed in the alerts table
    And <numberOfNewAlerts> alerts has been correctly added to statistics
    And <confidenceLevel> will not have 100% confidence level
    Examples:
      | confidenceLevel | numberOfNewAlerts | type   | confidence | id     | entityName     | country   | birth date | gender | nationality | jobTitle                         | address |
      | 100             | 1                 | person | 40         | 136625 | Chi Kwon,g Law | Hong Kong | 08/05/1927 | Male   | China       | Secretary for Labour and Welfare | HKONG   |
