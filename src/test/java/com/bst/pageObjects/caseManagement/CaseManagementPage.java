package com.bst.pageObjects.caseManagement;

import com.bst.base.BasePage;
import com.bst.commons.ScenarioContext;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.RandomStringUtils;
import com.bst.commons.enums.CaseManagementVault;

@Component
@Scope("cucumber-glue")
public class CaseManagementPage extends BasePage {

  @Autowired
  public ScenarioContext scenarioContext;

  private static final String CASE_ID_FILTERED_NONE_COLUMN_LOCATOR = "[col-id='caseId'] [class*='ag-header-cell-sorted-none']";

  private static final String CASE_ID_FILTERED_ASC_COLUMN_LOCATOR = "[col-id='caseId'] [class*='ag-header-cell-sorted-asc']";

  private static final String CASE_ID_FILTERED_DESC_COLUMN_LOCATOR = "[col-id='caseId'] [class*='ag-header-cell-sorted-desc']";

  private static final String EXISTED_CASE_WARNING_MESSAGE_LOCATOR = "[data-target='#existedCase']";

  private static final String CREATE_CASE_DROPDOWN_FRAME_LOCATOR = ".ng-trigger-transformPanel";

  @FindBy(css = ".submenu-wrapper")
  public WebElement headerMenuBar;

  @FindBy(css = ".add-feed-btn")
  public WebElement addCaseButton;

  @FindBy(css = "[name='entityType'] .mat-select-arrow")
  public WebElement entityTypesDropdown;

  @FindBy(css = "button.w-auto")
  public WebElement searchButton;

  @FindBy(css = "[value='organization']")
  public WebElement organizationOption;

  @FindBy(css = "[value='person']")
  public WebElement personOption;

  @FindBy(css = "[name='enityname']")
  public WebElement entityNameTextBox;

  @FindBy(css = "[placeholder='Country']")
  public WebElement countryTextBox;

  @FindBy(css = ".mat-option-text span.text-dark-black")
  public List<WebElement> countryOptionList;

  @FindBy(css = ".results-table div.custom-radio")
  public List<WebElement> searchResultLabelList;

  @FindBy(css = ".my-1 [type='button']")
  public List<WebElement> cancelAndCreateButtonList;

  @FindBy(css = "[name='caseType'] div.mat-select-arrow")
  public WebElement createCaseCaseTypeDropdownIcon;

  @FindBy(css = ".mat-option")
  public List<WebElement> createCaseDropdownsOptionsList;

  @FindBy(css = "[name='customerInternalID']")
  public WebElement createCaseCustomerIdTextBox;

  @FindBy(css = ".my-1 [type='button']")
  public List<WebElement> cancelPreviousCreateButtonList;

  @FindBy(css = ".mat-dialog-container .alert-success")
  public WebElement caseCreatedPopUp;

  @FindBy(css = ".mat-dialog-container button")
  public List<WebElement> goToCasePageOkButtonList;

  @FindBy(css = "[data-target='#existedCase']")
  public WebElement existedCaseWarningMessage;

  @FindBy(css = "div.text-right button")
  public List<WebElement> continueCaseCreationNoYesButtonList;

  @FindBy(css = "[class*='alert-success mt-4']")
  public WebElement caseSuccessfullyCreatedLabel;

  @FindBy(css = "[class='ng-tns-c4-0 ng-star-inserted'] .alert-success")
  public WebElement caseCreationSuccessToast;

  @FindBy(css = ".case-data")
  public WebElement caseDetailsWindow;

  @FindBy(css = ".mr-5 [class='mb-3 mt-3'] span:nth-child(2)")
  public WebElement caseDetailsWindowEntityNameLabel;

  @FindBy(css = "[class='material-icons c-pointer color-gray']")
  public WebElement closeCaseDetailsWindowButton;

  @FindBy(css = ".ag-floating-filter-input")
  public List<WebElement> caseFilterTextboxList;

  @FindBy(css = ".multiselect-dropdown")
  public WebElement filtersMenuDropdown;

  @FindBy(css = ".dropdown-list ul.item1")
  public WebElement filtersMenuDropdownSelectAllCheckbox;

  @FindBy(css = "[col-id='CaseName']")
  public List<WebElement> caseNameLabelList;

  @FindBy(css = "[col-id='caseId']")
  public List<WebElement> CaseIdLabelList;

  @FindBy(css = ".ag-body-horizontal-scroll-viewport")
  public WebElement horizontalScrollBar;

  @FindBy(css = ".ag-header-row [col-id='caseId']")
  public WebElement caseIdLabel;

  @FindBy(css = ".ag-icon-filter")
  public List<WebElement> filteredIcon;

  @FindBy(css = "[name='requester']")
  public WebElement createCaseRequesterDropdown;

  public CaseManagementPage(WebDriver driver) {
    super(driver);
  }

  public void fillSearchDetails(String entityType, String entityName, String country) {
    selectEntityType(entityType);
    entityNameTextBox.sendKeys(entityName);
    countrySearch(country);
  }

  public void selectFirstResultOnList() {
    wait.waitForElementToBeVisible(searchResultLabelList.get(0));
    searchResultLabelList.get(0).click();
    cancelAndCreateButtonList.get(1).click();
  }

  public void fillCaseDetails(String caseType) {
    selectCaseType(caseType);
    selectFirstRequester();
    String customerId = "automation " + RandomStringUtils.randomAlphanumeric(4).toUpperCase();
    scenarioContext.setToContainer(CaseManagementVault.CUSTOMER_INTERNAL_ID,
        customerId);
    fillCustomerId(customerId);
  }

  public void selectFirstRequester() {
    wait.waitForElementToBeVisible(createCaseRequesterDropdown);
    createCaseRequesterDropdown.click();
    wait.waitForElementExistAndVisible(By.cssSelector(CREATE_CASE_DROPDOWN_FRAME_LOCATOR),5, 100);
    createCaseDropdownsOptionsList.get(0).click();
  }

  public void closeWarningPopUp() {
    if (isElementPresent(By.cssSelector(EXISTED_CASE_WARNING_MESSAGE_LOCATOR))
  ) {
      continueCaseCreationNoYesButtonList.get(1).click();
    }
  }

  public void goToCasePageFromConfirmationPopUp() {
    wait.waitForElementToBeVisible(caseSuccessfullyCreatedLabel);
    wait.waitForInvisibilityOfElement(caseCreationSuccessToast);
    click(goToCasePageOkButtonList.get(0));
  }

  public void goToCaseListPageFromConfirmationPopUp() {
    wait.waitForElementToBeVisible(caseSuccessfullyCreatedLabel);
    click(goToCasePageOkButtonList.get(1));
  }

  public void sortCaseTableByCaseId(String sorting) {
    wait.waitForElementToBeVisible(caseIdLabel);
    switch (sorting) {
      case "NONE":
        while (!isElementPresent(By.cssSelector(CASE_ID_FILTERED_NONE_COLUMN_LOCATOR))) {
          caseIdLabel.click();
        }
        break;
      case "ASC":
        while (!isElementPresent(By.cssSelector(CASE_ID_FILTERED_ASC_COLUMN_LOCATOR))) {
          caseIdLabel.click();
        }
        break;
      case "DESC":
        while (!isElementPresent(By.cssSelector(CASE_ID_FILTERED_DESC_COLUMN_LOCATOR))) {
          caseIdLabel.click();
        }
        break;
      default:
        throw new IllegalArgumentException("Incorrect sorting type");
    }
  }

  private void selectCaseType(String caseType) {
    wait.waitForElementToBeVisible(createCaseCaseTypeDropdownIcon);
    click(createCaseCaseTypeDropdownIcon);
    switch (caseType) {
      case "new":
        createCaseDropdownsOptionsList.get(0).click();
        break;
      case "refresh":
        createCaseDropdownsOptionsList.get(1).click();
        break;
      case "re work":
        createCaseDropdownsOptionsList.get(2).click();
        break;
      case "trigger event":
        createCaseDropdownsOptionsList.get(3).click();
        break;
      default:
        throw new IllegalArgumentException("Incorrect case type");
    }
  }

  private void fillCustomerId(String customerId) {
    createCaseCustomerIdTextBox.clear();
    createCaseCustomerIdTextBox.sendKeys(customerId);
  }

  private void selectEntityType(String entityType) {
    wait.waitForElementToBeVisible(searchButton);
    click(entityTypesDropdown);
    switch (entityType) {
      case "company":
        organizationOption.click();
        break;
      case "person":
        personOption.click();
        break;
      default:
        throw new IllegalArgumentException("Incorrect entity type");
    }
  }

  private void countrySearch(String country) {
    wait.waitForElementToBeVisible(countryTextBox);
    countryTextBox.click();
    click(countryTextBox);
    for (int character = 0; character < country.length(); character++) {
      actions.sendKeys("" + country.charAt(character)).perform();
    }
    countryOptionList.stream()
        .filter(e -> e.getText().contains(country))
        .findFirst()
        .get()
        .click();
  }

}