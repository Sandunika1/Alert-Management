package com.bst.stepDefinitions.caseManagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import com.bst.base.BaseSteps;
import com.bst.commons.ScenarioContext;
import com.bst.pageObjects.caseManagement.CaseManagementPage;
import com.bst.pageObjects.home.HomePage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class CaseManagementSteps extends BaseSteps {

  @Autowired
  public CaseManagementPage caseManagementPage;

  @Autowired
  public HomePage homePage;

  @Autowired
  public ScenarioContext scenarioContext;

  @When("go to case management page")
  public void goToCaseManagementPage() {
    homePage.riskTile.click();
    wait.waitForElementToBeVisible(homePage.casesTile);
    homePage.casesTile.click();
    wait.waitForElementToBeVisible(caseManagementPage.headerMenuBar);
  }

  @When("search for case with {string} entity type, {string}  name, {string} country on Case Management page")
  public void addCaseFor(String entityType, String entityName, String country) {
    wait.waitForElementToBeClickable(caseManagementPage.addCaseButton);
    caseManagementPage.addCaseButton.click();
    caseManagementPage.fillSearchDetails(entityType, entityName, country);
    caseManagementPage.searchButton.click();
    caseManagementPage.selectFirstResultOnList();
  }

  @When("add case with details {string} case type, admin requester and random customer ID")
  public void addCaseDetails(String caseType) {
      caseManagementPage.fillCaseDetails(caseType);
      caseManagementPage.cancelPreviousCreateButtonList.get(2).click();
  }

  @When("go to case details from confirmation pop up")
  public void goToCaseDetailsFromConfirmationPopUp() {
    caseManagementPage.closeWarningPopUp();
    caseManagementPage.goToCasePageFromConfirmationPopUp();
  }

  @When("go to case list from confirmation pop up")
  public void goToCaseListPageFromConfirmationPopUp() {
    caseManagementPage.closeWarningPopUp();
    caseManagementPage.goToCaseListPageFromConfirmationPopUp();
  }

  @When("sort case table by Case ID {string}")
  public void sortCaseTableByCaseId(String sorting) {
    caseManagementPage.sortCaseTableByCaseId(sorting);
  }

  @Then("entity name {string} is present on case details window")
  public void entityNamePresentOnCaseDetailsWindow(String name) throws IOException {
    wait.waitForElementToBeVisible(caseManagementPage.caseDetailsWindow);
    wait.waitForElementToBeVisible(caseManagementPage.caseDetailsWindowEntityNameLabel);
    assertThat(caseManagementPage.caseDetailsWindowEntityNameLabel.getText(), containsString(name));
    caseManagementPage.closeCaseDetailsWindowButton.click();
  }

  @Then("entity name {string} is present in case list")
  public void entityNameIsPresentInCaseList(String name) {
    wait.waitForElementToBeVisible(caseManagementPage.caseNameLabelList.get(1));
    assertThat(caseManagementPage.caseNameLabelList.get(1).getText(), containsString(name));
  }

  @When("search newly created case in table by Customer ID")
  public void searchForNewlyCreatedCaseByCustomerId() {
    Object customerId = scenarioContext.getFromContainer("CUSTOMER_INTERNAL_ID");
    getActions()
        .click(caseManagementPage.caseFilterTextboxList.get(1))
        .sendKeys(customerId.toString())
        .perform();
    wait.waitForElementToBeVisible(caseManagementPage.filteredIcon.get(1));
  }

}
