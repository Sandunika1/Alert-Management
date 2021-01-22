package com.bst.stepDefinitions.alertManagement;

import com.bst.base.BaseSteps;
import com.bst.commons.ScenarioContext;
import com.bst.commons.filereaders.DataMapper;
import com.bst.commons.filereaders.ResourceReader;
import com.bst.pageObjects.alertManagement.AlertManagementPage;
import com.bst.pageObjects.alertManagement.AlertManagementUploadWindowPage;
import com.bst.pageObjects.home.HomePage;
import com.bst.pageObjects.home.LoginPage;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class AlertManagementUploadWindowSteps extends BaseSteps {
  @Autowired public LoginPage loginPage;
  @Autowired public ScenarioContext scenarioContext;
  @Autowired public HomePage homePage;
  @Autowired public AlertManagementPage alertManagementPage;
  @Autowired public AlertManagementUploadWindowPage fileUploadPage;
  @Autowired public DataMapper data;

  private final String PATH_TO_FILE = "filestoupload/";

  @When("Upload {string} batch file with {string}% of confidence")
  public void uploadBatchFileWithOfConfidence(String fileName, String confidencePercentage) {
    wait.waitForElementToBeVisible(alertManagementPage.uploadButton);
    scenarioContext.setToContainer(
        "NUMBER_OF_ALERTS_IN_STATISTICS",
        alertManagementPage.getTotalNumberOfAlertsBasedOnStatistics());
    alertManagementPage.sortDownAlertId();
    scenarioContext.setToContainer(
        "LATEST_ALERT_ID_BEFORE_GENERATING_NEW_ALERTS", alertManagementPage.getLatestAlertId());
    wait.waitForElementToBeVisible(alertManagementPage.uploadButton);
    alertManagementPage.uploadButton.click();
    wait.waitForElementToBeVisible(fileUploadPage.fileUploadTitle);
    fileUploadPage.uploadFileButton.sendKeys(
        new ResourceReader().getPathToFile(PATH_TO_FILE + fileName));
    fileUploadPage.selectConfidenceLevel(confidencePercentage);
    fileUploadPage.fileUploadTitle.click();
    fileUploadPage.doubleClickOnRunButton();
  }
}
