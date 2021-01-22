package com.bst.stepDefinitions.advancedsearch;

import com.bst.base.BasePage;
import com.bst.pageObjects.advancedsearch.AdvancedSearchPage;
import com.bst.pageObjects.advancedsearch.CompanyFormPage;
import com.bst.pageObjects.advancedsearch.PersonFormPage;
import com.bst.pageObjects.entity.company.compliance.CompliancePage;
import com.bst.pageObjects.home.HomePage;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.bst.base.BaseSteps;

@Scope("cucumber-glue")
public class AdvancedSearchPageSteps extends BaseSteps {

  @Autowired public AdvancedSearchPage advancedSearchPage;

  @Autowired public CompanyFormPage companyFormPage;

  @Autowired public PersonFormPage personFormPage;

  @Autowired public HomePage homePage;

  @Autowired public CompliancePage compliancePage;

  @When("Search for {string} entity type with name {string} in {string} jurisdiction")
  public void searchForEntity(String entityType, String entityName, String jurisdiction) {
    advancedSearchPage.selectEntityType(entityType);
    companyFormPage.fillCompanySearchForm(entityName, jurisdiction);
    advancedSearchPage.searchButton.click();
  }

  @When("go to advanced search page")
  public void goToAdvancedSearch() {
    homePage.riskTile.click();
    wait.waitForElementToBeVisible(homePage.advancedSearchTile);
    homePage.advancedSearchTile.click();
  }

  @When("create new case from advanced search")
  public void createNewCaseFromAdvancedSearch() {
    advancedSearchPage.createNewCase();
  }
  ;

  @When("go to first search result entity page")
  public void goToFirstSearchResultEntityPage() {
    advancedSearchPage.selectFirstResultOnList();
    // todo: change it to some more generic element when person entity functionality will be
    // implemented
    wait.waitForElementToBeVisible(compliancePage.riskScoreLabel);
  }
}
