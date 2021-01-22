package com.bst.pageObjects.entity.company.compliance.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CompanyDetailElement {

  private final By sourceButtonLocator = By.cssSelector(".fa-link");
  private final By editButtonLocator = By.cssSelector(".fa-pencil");
  private final By companyDetailsValueLocator = By.cssSelector("span[ng-if]");
  private final By propertyLabel = By.cssSelector("div.left p");
  private final WebElement element;

  public CompanyDetailElement(WebElement element) {
    this.element = element;
  }

  public String getValue() {
    return element.findElement(companyDetailsValueLocator).getText();
  }

  public String getPropertyLabel() {
    return element.findElement(propertyLabel).getText();
  }

  public void clickEditButton() {
    element.findElement(editButtonLocator).click();
  }

  public void clickSourceButton() {
    element.findElement(sourceButtonLocator).click();
  }

  public WebElement getRawElement(){
    return element;
  }
}
