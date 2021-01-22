@login
Feature: Login feature

  Scenario: Login as a federation user
    Given Log in as "federation@bstai.onmicrosoft.com" user

  Scenario: Login as a Admin user
    Given Log in as Admin "admin" user

  Scenario: Login as a Analyst user
    Given Log in as Analyst "analyst" user