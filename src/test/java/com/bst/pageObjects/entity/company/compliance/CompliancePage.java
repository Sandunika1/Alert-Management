package com.bst.pageObjects.entity.company.compliance;

import com.bst.base.BasePage;
import com.bst.locators.FindByNg;
import com.bst.pageObjects.entity.company.compliance.elements.CompanyDetailElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("cucumber-glue")
public class CompliancePage extends BasePage {

  public CompliancePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = ".company-title")
  public WebElement companyTitleLabel;

  @FindByNg(ifStatement = "Duediligence")
  public WebElement riskScoreLabel;

  // need to be fixed - findbyng is not returning list
  @FindBy(css = "[ng-repeat*='conplianceMapKeys']")
  public List<WebElement> listOfCompanyDetails;

  @FindBy(css = "[permission*='companyIdentifiers']")
  public List<WebElement> listOfCompanyIdentifiers;

  @FindBy(css = "[uib-accordion-group] [id*=accordion]")
  public List<WebElement> sourceAccordion;

  @FindByNg(click = "closeDataPopUp()")
  public WebElement closePopupIcon;

  public List<CompanyDetailElement> getCompanyDetails() {
    List<CompanyDetailElement> details = new ArrayList<>();
    for (WebElement element : listOfCompanyDetails) {
      details.add(new CompanyDetailElement(element));
    }
    return details;
  }

  public List<CompanyDetailElement> getCompanyIdentifiers() {
    List<CompanyDetailElement> details = new ArrayList<>();
    for (WebElement element : listOfCompanyIdentifiers) {
      details.add(new CompanyDetailElement(element));
    }
    return details;
  }

  public void expandSourceAccordions() {
    for (WebElement accordion : sourceAccordion) {
      js.setAttribute(accordion, "aria-expanded", "true");
    }
  }
}
