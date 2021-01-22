package com.bst.stepDefinitions.home;

import com.bst.base.BaseSteps;
import com.bst.commons.filereaders.DataMapper;
import com.bst.configuration.Config;
import com.bst.pageObjects.home.HomePage;
import com.bst.pageObjects.home.LoginPage;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("cucumber-glue")
public class LoginPageSteps extends BaseSteps {

  @Autowired public Config config;

  @Autowired public LoginPage loginPage;

  @Autowired public HomePage homePage;

  @Autowired public DataMapper data;

  @Given("Log in as {string} user")
  public void logInAs(String userName) {
    wait.waitForElementToBeVisible(loginPage.logoImage);
    loginPage.logInAs(userName);
    wait.waitForElementToBeVisible(homePage.riskTile);
  }

  @Given("Log in as Admin {string} user")
  public void logInAsUserNameUser(String adminUser) {
    wait.waitForElementToBeVisible(loginPage.logoImage);
    loginPage.logInAs(adminUser);
    wait.waitForElementToBeVisible(homePage.riskTile);
  }

  @Given("Log in as Analyst {string} user")
  public void logInAsAnalystUser(String analystUser) {
    wait.waitForElementToBeVisible(loginPage.logoImage);
    loginPage.logInAs(analystUser);
    wait.waitForElementToBeVisible(homePage.riskTile);
  }
}
