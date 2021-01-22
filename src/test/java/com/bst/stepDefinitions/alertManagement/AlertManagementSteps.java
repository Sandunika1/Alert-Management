package com.bst.stepDefinitions.alertManagement;

import com.bst.base.BaseSteps;
import com.bst.commons.filereaders.DataMapper;
import com.bst.pageObjects.alertManagement.AlertManagementPage;
import com.bst.pageObjects.home.HomePage;
import com.bst.pageObjects.home.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.testng.Assert;

@Scope("cucumber-glue")
public class AlertManagementSteps extends BaseSteps {

  @Autowired public LoginPage loginPage;
  @Autowired public HomePage homePage;
  @Autowired public AlertManagementPage alertManagementPage;
  @Autowired public DataMapper data;

  @And("Go to the alert management Page")
  public void goToTheAlertManagementPage() {
    homePage.riskTile.click();
    wait.waitForElementToBeVisible(homePage.alertManagementTile);
    homePage.alertManagementTile.click();
    alertManagementPage.switchToAlertManagementWindow();
  }

  @Then("Alert Management title has been displayed")
  public void alertManagementTitleHasBeenDisplayed() {
    wait.waitForElementToBeVisible(alertManagementPage.alertManagementTitle);
    Assert.assertTrue(
            alertManagementPage.alertManagementTitle.isDisplayed());
  }
  }
