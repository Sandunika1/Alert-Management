package com.bst.pageObjects.alertManagement;

import com.bst.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("cucumber-glue")
public class AlertManagementSearchWindowPage extends BasePage {

  @FindBy(css = ".ng-star-inserted h3.text-capitalize")
  public WebElement searchTextTitle;

  @FindBy(css = "mat-slider")
  public WebElement confidenceSlider;

  @FindBy(name = "entityType")
  public WebElement entityTypeDropDown;

  @FindBy(name = "enityId")
  public WebElement entityIdTextInput;

  @FindBy(name = "enityname")
  public WebElement entityNameTextInput;

  @FindBy(name = "countryOrigin")
  public WebElement countryOfRegistrationDropDown;

  @FindBy(css = "input[name='birth']")
  public WebElement dateOfRegistrationTextInput;

  @FindBy(name = "address")
  public WebElement addressTextInput;

  @FindBy(css = "[name='gender']")
  public WebElement genderTypeDropDown;

  @FindBy(css = "div [name ='nationality']")
  public WebElement selectNationality;

  @FindBy(css = "input[placeholder='search']")
  public WebElement nationalityTextInput;

  @FindBy(css = ".mat-option-text .flag-icon")
  public WebElement nationality;

  @FindBy(name = "jobTitle")
  public WebElement jobTitleTextInput;

  @FindBy(name = "address")
  public WebElement AddressTextInput;

  @FindBy(css = "form.search-popover button:nth-of-type(2)")
  public WebElement searchButton;

  @FindBy(css = "[role='option']")
  public List<WebElement> selectCountry;

  public AlertManagementSearchWindowPage(WebDriver driver) {
    super(driver);
  }

  public void setConfidenceLevel(String confidencePercentage) {
    js.setAttribute(confidenceSlider, "aria-valuenow", confidencePercentage);
  }

  public void selectEntity(String entityType) {
    wait.waitForElementToBeVisible(searchTextTitle);
    entityTypeDropDown.click();
    switch (entityType) {
      case "person":
        driver.findElement(By.cssSelector("mat-option[value='person']")).click();
        break;
      case "organization":
        driver.findElement(By.cssSelector("mat-option[value='organization']")).click();
        break;
      default:
        throw new IllegalArgumentException("Incorrect entity type");
    }
  }

  public void selectGender(String genderType) {
    js.clickElement(genderTypeDropDown);
    wait.forPage(30);
    switch (genderType) {
      case "Male":
        driver.findElement(By.cssSelector("mat-option[value='male']")).click();
        break;
      case "female":
        driver.findElement(By.cssSelector("mat-option[value='female']")).click();
        break;
      default:
        throw new IllegalArgumentException("Incorrect gender type");
    }
  }
  public void setCountryOfRegistration(String countryOfRegistration) {
    countryOfRegistrationDropDown.click();
    for (int character = 0; character < countryOfRegistration.length(); character++) {
      new Actions(driver).sendKeys("" + countryOfRegistration.charAt(character)).perform();
      wait.waitForElementToBeVisible(By.cssSelector("[aria-expanded='true']"));
    }
    List<WebElement> countries = driver.findElements(By.cssSelector("[role='option'] span"));
    wait.forPage();
    countries.stream()
        .filter(e -> e.getText().equals(countryOfRegistration))
        .findFirst()
        .get()
        .click();
  }
}
