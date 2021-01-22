package com.bst.pageObjects.alertManagement;

import com.bst.base.BasePage;
import com.google.common.collect.Iterables;
import lombok.var;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("cucumber-glue")
public class AlertManagementPage extends BasePage {
  @FindBy(css = "app-breadcrumb span:nth-child(3)")
  public WebElement alertManagementTitle;

  @FindBy(css = ".upload-file-btn.upload-header-button:nth-child(1)")
  public WebElement searchButton;

  @FindBy(css = ".upload-file-btn.upload-header-button:nth-child(2)")
  public WebElement uploadButton;

  @FindBy(css = "div[col-id='alertId'] [role='columnheader']")
  public WebElement alertIdColumnList;

  @FindBy(css = ".alert-success[role='alert']")
  public WebElement searchIsInitiatedToast;

  @FindBy(css = ".alerts-generated.alert-success span .ng-star-inserted")
  public WebElement successToastOfNewAlerts;

  @FindBy(css = ".alerts-generated[type='success']>p:last-child")
  public WebElement successToastRefreshButton;

  @FindBy(css = "mat-select .mat-select-arrow-wrapper")
  public WebElement rowsPerPageDropDown;

  @FindBy(css = "mat-option")
  public List<WebElement> rowsPerPageOptionsList;

  @FindBy(id = "dropdownForm")
  public WebElement viewDropDown;

  @FindBy(css = ".dropdown-control-wrapper label p")
  public List<WebElement> viewOptionList;

  @FindBy(css = ".ag-center-cols-viewport [role='row']")
  public List<WebElement> alertRowList;

  @FindBy(css = ".bar-text")
  public List<WebElement> statisticsLabelList;

  private static final By ID_COLUMN_LOCATOR = By.cssSelector("span.ag-group-value");

  public AlertManagementPage(WebDriver driver) {
    super(driver);
  }

  public void switchToAlertManagementWindow() {
    switchToTab(1);
  }

  public void selectLastOptionInRowsPerPageDropDown() {
    click(rowsPerPageDropDown);
    click(Iterables.getLast(rowsPerPageOptionsList));
    wait.forPage();
  }

  public void selectViewByIndex(int index) {
    click(viewDropDown);
    click(viewOptionList.get(index));
    wait.forPage();
  }

  public List<WebElement> getNewAlertsRows(String searchedId) {
    var elements = new ArrayList<WebElement>();
    wait.waitForElementToBeVisible(ID_COLUMN_LOCATOR);
    var alertRow =
        alertRowList.stream().filter(e -> e.getText().contains(searchedId)).findAny().orElse(null);
    wait.forPage();
    return elements;
  }

  public int getTotalNumberOfAlertsBasedOnStatistics() {
    var total = 0;
    for (var stat : statisticsLabelList) {
      if (stat.getText().isEmpty()) {
        total += 0;
      } else {
        total += Integer.parseInt(stat.getText());
      }
    }
    return total;
  }

  public String getLatestAlertId() {
    sortDownAlertId();
    wait.forPage();
    return alertRowList.get(0).findElement(ID_COLUMN_LOCATOR).getText();
  }

  public void sortDownAlertId() {
    alertIdColumnList.click();
    wait.forPage(40);
    alertIdColumnList.click();
    wait.forPage(40);
  }
  public String getBeforeAlertId() {
    sortDownAlertId();
    wait.forPage();
    return alertRowList.get(1).findElement(ID_COLUMN_LOCATOR).getText();
  }
}
