package com.bst.pageObjects.advancedsearch;

import com.bst.base.BasePage;
import com.bst.locators.FindByNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AdvancedSearchPage extends BasePage {

  @FindBy(css = "#headingcompany #single-button")
  public WebElement entityTypesDropdown;

  @FindByNg(click = "onChangeEntityType('Company')")
  public WebElement companyOption;

  @FindByNg(click = "onChangeEntityType('Person')")
  public WebElement personOption;

  @FindByNg(click = "delaySearchutility()")
  public WebElement searchButton;

  @FindByNg(repeat = "entitiesSearchResolveResult")
  public WebElement searchResultLabelList; //todo: make it as a list

  @FindByNg(click = "onClickOpenCreateCaseDialog(data)")
  public WebElement createCaseButton;

  public AdvancedSearchPage(WebDriver driver) {
    super(driver);
  }

  public void selectEntityType(String entityType) {
    wait.waitForElementToBeVisible(searchButton);
    click(entityTypesDropdown);
    switch (entityType) {
      case "company":
        companyOption.click();
        break;
      case "person":
        // todo: functionality is not implemented yet in application
        break;
      default:
        throw new IllegalArgumentException("Incorrect entity type");
    }
  }

  public void selectFirstResultOnList(){
    wait.waitForElementToBeVisible(searchResultLabelList);
    searchResultLabelList.click();
    switchToTab(1);
  }

  public void createNewCase(){
    createCaseButton.click();
    switchToTab(1);
  }

}
