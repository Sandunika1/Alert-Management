@compliance
Feature: Compliance

  Scenario: Entity details are displayed properly on compliance page
    Given Log in as "federation@bstai.onmicrosoft.com" user
    And go to advanced search page
    When Search for "company" entity type with name "Adidas" in "German" jurisdiction
    And go to first search result entity page
    Then Company details are identical to the data in the "eng-com-adidas-company-details.yaml" file

  Scenario: Entity identifiers are displayed properly on compliance page
    Given Log in as "federation@bstai.onmicrosoft.com" user
    And go to advanced search page
    When Search for "company" entity type with name "Adidas" in "German" jurisdiction
    And go to first search result entity page
    Then Company identifiers are identical to the data in the "eng-com-adidas-company-identifiers.yaml" file