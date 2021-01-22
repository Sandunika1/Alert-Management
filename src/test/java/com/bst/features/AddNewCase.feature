@addCase @AP-1398 @CaseWorkbench
Feature: Add New Case from case management page

  #EL-T967
  Scenario: Case name is present on case details window after new case added
    Given Log in as "federation@bstai.onmicrosoft.com" user
    And go to case management page
    When search for case with "company" entity type, "Adidas"  name, "German" country on Case Management page
    And add case with details "new" case type, admin requester and random customer ID
    And go to case details from confirmation pop up
    Then entity name "Reebok" is present on case details window

  #EL-T967
  Scenario: Case name is present in the case list after new case added
    Given Log in as "federation@bstai.onmicrosoft.com" user
    And go to case management page
    When search for case with "company" entity type, "Adidas"  name, "German" country on Case Management page
    And add case with details "new" case type, admin requester and random customer ID
    And go to case list from confirmation pop up
    And search newly created case in table by Customer ID
    Then entity name "Adidas" is present in case list

    Scenario: Case name is present on case details window after new case added from Advanced search
      Given Log in as "federation@bstai.onmicrosoft.com" user
      And go to advanced search page
      When Search for "company" entity type with name "Adidas" in "Germany" jurisdiction
      And create new case from advanced search
      And add case with details "new" case type, admin requester and random customer ID
      And go to case details from confirmation pop up
      Then entity name "Adidas" is present on case details window