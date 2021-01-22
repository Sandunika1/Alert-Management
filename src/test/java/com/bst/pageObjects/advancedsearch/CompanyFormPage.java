package com.bst.pageObjects.advancedsearch;

import com.bst.base.BasePage;
import com.bst.locators.FindByNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("cucumber-glue")
public class CompanyFormPage extends BasePage {

  @FindBy(css = "#companyname")
  public WebElement companyNameTextBox;

  @FindBy(css = "[placeholder*='Jurisdictions']")
  public WebElement jurisdictionTextBox;

  @FindByNg(click = "updateCompanyParam()")
  public WebElement updateButton;

  @FindByNg(click = "close('company')")
  public WebElement cancelButton;

  @FindBy(css = "li.uib-typeahead-match span:nth-of-type(2)")
  public List<WebElement> jurisdictionOptionList;

  public CompanyFormPage(WebDriver driver) {
    super(driver);
  }

  public void fillCompanySearchForm(String entityName, String jurisdiction) {
    companyNameTextBox.sendKeys(entityName);
    countrySearch(jurisdiction);
    click(updateButton, 10);
  }

  private void countrySearch(String country) {
    wait.waitForElementToBeVisible(jurisdictionTextBox);
    jurisdictionTextBox.click();
    click(jurisdictionTextBox);
    for (int character = 0; character < country.length(); character++) {
      actions.sendKeys("" + country.charAt(character)).perform();
    }
    jurisdictionOptionList.stream()
        .filter(e -> e.getText().contains(country))
        .findFirst()
        .get()
        .click();
  }
}
