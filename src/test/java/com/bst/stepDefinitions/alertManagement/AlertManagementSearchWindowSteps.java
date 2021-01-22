package com.bst.stepDefinitions.alertManagement;

import com.bst.base.BaseSteps;
import com.bst.commons.ScenarioContext;
import com.bst.commons.filereaders.DataMapper;
import com.bst.pageObjects.alertManagement.AlertManagementPage;
import com.bst.pageObjects.alertManagement.AlertManagementSearchWindowPage;
import com.bst.pageObjects.home.HomePage;
import com.bst.pageObjects.home.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.var;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.testng.Assert;

import static org.hamcrest.Matchers.greaterThan;

@Scope("cucumber-glue")
public class AlertManagementSearchWindowSteps extends BaseSteps {
  @Autowired public LoginPage loginPage;
  @Autowired public ScenarioContext scenarioContext;
  @Autowired public HomePage homePage;
  @Autowired public AlertManagementPage alertManagementPage;
  @Autowired public AlertManagementSearchWindowPage alertManagementSearchWindowPage;
  @Autowired public DataMapper data;

  @When(
      "Search for entity type: {string}, confidence: {string}%, id: {string}, entity name: {string}, country: {string},country Of Registration: {string},address: {string}")
  public void searchForEntityTypeConfidenceIdEntityNameCountryCountryOfRegistrationAddress(
      String entityType,
      String confidence,
      String id,
      String entityName,
      String country,
      String countryOfRegistration,
      String address)
      throws InterruptedException {
    wait.waitForElementToBeVisible(alertManagementPage.searchButton);
    wait.waitForElementToBeVisible(alertManagementPage.alertIdColumnList);
    scenarioContext.setToContainer(
        "NUMBER_OF_ALERTS_IN_STATISTICS",
        alertManagementPage.getTotalNumberOfAlertsBasedOnStatistics());
    alertManagementPage.sortDownAlertId();
    scenarioContext.setToContainer(
        "LATEST_ALERT_ID_BEFORE_GENERATING_NEW_ALERTS", alertManagementPage.getLatestAlertId());
    wait.waitForElementToBeVisible(alertManagementPage.searchButton);
    alertManagementPage.searchButton.click();
    wait.waitForElementToBeVisible(alertManagementSearchWindowPage.searchTextTitle);
    alertManagementSearchWindowPage.setConfidenceLevel(confidence);
    alertManagementSearchWindowPage.selectEntity(entityType);
    alertManagementSearchWindowPage.entityIdTextInput.sendKeys(id);
    alertManagementSearchWindowPage.entityNameTextInput.sendKeys(entityName);
    alertManagementSearchWindowPage.setCountryOfRegistration(country);
    wait.forPage();
    alertManagementSearchWindowPage.dateOfRegistrationTextInput.click();
    wait.forPage();
    alertManagementSearchWindowPage.dateOfRegistrationTextInput.sendKeys(countryOfRegistration);
    alertManagementSearchWindowPage.addressTextInput.sendKeys(address);
    wait.forPage();
    js.clickElement(alertManagementSearchWindowPage.searchButton);
  }

  @Then("Search initiated toast has been displayed")
  public void searchInitiatedToastHasBeenDisplayed() {
    wait.waitForElementToBeVisible((alertManagementPage.searchIsInitiatedToast));
    Assert.assertTrue(alertManagementPage.searchIsInitiatedToast.isDisplayed());
  }

  @And("Toast with {string} alerts has been displayed")
  public void toastWithAlertsHasBeenDisplayed(String numberOfAlerts) {
    Assert.assertTrue(
        alertManagementPage.successToastOfNewAlerts.getText().contains(numberOfAlerts));
  }

  @And("{int} new alerts are displayed in the alerts table")
  public void newAlertsAreDisplayedInTheAlertsTable(int numberOfAlerts) {
    alertManagementPage.successToastRefreshButton.click();
    wait.forPage();
    alertManagementPage.selectLastOptionInRowsPerPageDropDown();
    wait.forPage();
    alertManagementPage.sortDownAlertId();
    Object lastAlertIdBeforeFileUpload =
        scenarioContext.getFromContainer("LATEST_ALERT_ID_BEFORE_GENERATING_NEW_ALERTS");
    var numberOfNewRecords =
        alertManagementPage.getNewAlertsRows(lastAlertIdBeforeFileUpload.toString()).size();
  }

  @And("{int} alerts has been correctly added to statistics")
  public void alertsHasBeenCorrectlyAddedToStatistics(int numberOfAlerts) {
    Object numberOfAlertsBeforeUpload =
        scenarioContext.getFromContainer("NUMBER_OF_ALERTS_IN_STATISTICS");
    var NumberOfAlerts = Integer.parseInt(numberOfAlertsBeforeUpload + "");
    var CorrectNumberOfAlerts = (NumberOfAlerts + numberOfAlerts);
    alertManagementPage.alertIdColumnList.click();
    wait.forPage(30);
    Object lastAlertIdBeforeFileUpload =
        scenarioContext.getFromContainer("LATEST_ALERT_ID_BEFORE_GENERATING_NEW_ALERTS");
    Assert.assertEquals(lastAlertIdBeforeFileUpload, alertManagementPage.getBeforeAlertId());
    MatcherAssert.assertThat(
        alertManagementPage.getTotalNumberOfAlertsBasedOnStatistics(),
        greaterThan((NumberOfAlerts)));
    Assert.assertEquals(
        alertManagementPage.getTotalNumberOfAlertsBasedOnStatistics(), (CorrectNumberOfAlerts));
  }

  @When(
      "Search for entity type: {string}, confidence: {string}%, id: {string}, entity name: {string}, country: {string}, birth date: {string}, gender: {string}, nationality: {string}, jobTitle: {string}, address: {string}")
  public void searchForAlerts(
      String entityType,
      String confidence,
      String id,
      String entityName,
      String country,
      String birthDate,
      String gender,
      String nationality,
      String jobTitle,
      String address)
      throws InterruptedException {
    wait.waitForElementToBeVisible(alertManagementPage.searchButton);
    wait.waitForElementToBeVisible(alertManagementPage.alertIdColumnList);
    scenarioContext.setToContainer(
        "NUMBER_OF_ALERTS_IN_STATISTICS",
        alertManagementPage.getTotalNumberOfAlertsBasedOnStatistics());
    scenarioContext.setToContainer(
        "LATEST_ALERT_ID_BEFORE_GENERATING_NEW_ALERTS", alertManagementPage.getLatestAlertId());
    wait.waitForElementToBeVisible(alertManagementPage.searchButton);
    alertManagementPage.searchButton.click();
    wait.waitForElementToBeVisible(alertManagementSearchWindowPage.searchTextTitle);
    alertManagementSearchWindowPage.setConfidenceLevel(confidence);
    alertManagementSearchWindowPage.selectEntity(entityType);
    alertManagementSearchWindowPage.entityIdTextInput.sendKeys(id);
    alertManagementSearchWindowPage.entityNameTextInput.sendKeys(entityName);
    alertManagementSearchWindowPage.setCountryOfRegistration(country);
    wait.forPage();
    alertManagementSearchWindowPage.dateOfRegistrationTextInput.click();
    wait.forPage();
    alertManagementSearchWindowPage.dateOfRegistrationTextInput.sendKeys(birthDate);
    wait.forPage();
    alertManagementSearchWindowPage.selectGender(gender);
    js.clickElement(alertManagementSearchWindowPage.selectNationality);
    wait.forPage(20);
    alertManagementSearchWindowPage.nationalityTextInput.sendKeys(nationality);
    wait.forPage();
    alertManagementSearchWindowPage.jobTitleTextInput.sendKeys(jobTitle);
    alertManagementSearchWindowPage.addressTextInput.sendKeys(address);
    js.clickElement(alertManagementSearchWindowPage.searchButton);
  }

  // To do default alert table is not displayed by current date
  @And("{int} will not have {int}% confidence level")
  public void confidenceLevelWillNotHaveConfidenceLevel(int confidenceLevel) {}
}
