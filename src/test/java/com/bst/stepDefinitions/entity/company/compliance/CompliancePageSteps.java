package com.bst.stepDefinitions.entity.company.compliance;

import static org.hamcrest.core.StringContains.containsString;

import com.bst.base.BaseSteps;
import com.bst.commons.filereaders.DataMapper;
import com.bst.logger.ConsolePrinter;
import com.bst.pageObjects.entity.company.compliance.CompliancePage;
import com.bst.pageObjects.entity.company.compliance.elements.CompanyDetailElement;
import io.cucumber.java.en.Then;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.var;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import static org.hamcrest.MatcherAssert.assertThat;

@Scope("cucumber-glue")
public class CompliancePageSteps extends BaseSteps {

  @Autowired public DataMapper dataMapper;
  @Autowired public CompliancePage compliancePage;
  @Autowired public ConsolePrinter consolePrinter;

  @Then("Company details are identical to the data in the {string} file")
  public void verifyCompanyDetails(String fileName) throws IOException {
    compareDataFromFileToDataOnPage(fileName, compliancePage.getCompanyDetails());
  }

  @Then("Company identifiers are identical to the data in the {string} file")
  public void verifyCompanyIdentifiers(String fileName) throws IOException {
    compareDataFromFileToDataOnPage(fileName, compliancePage.getCompanyIdentifiers());
  }

  public void compareDataFromFileToDataOnPage(
      String fileName, List<CompanyDetailElement> actualResults) throws IOException {
    var valuesFromFile = dataMapper.getYamlFileAsMap(fileName);
    for (Map.Entry<String, List<String>> valueFromFile : valuesFromFile.entrySet()) {
      var valueFromPage =
          actualResults.stream()
              .filter(str -> str.getPropertyLabel().contains(valueFromFile.getKey()))
              .findFirst()
              .get();
      // actions call is needed because frontend is full of defects
      getActions()
          .click(valueFromPage.getRawElement())
          .keyDown(Keys.CONTROL)
          .sendKeys(Keys.END)
          .perform();
      for (String expectedResults : valueFromFile.getValue()) {
        assertThat(valueFromPage.getValue().trim(), containsString(expectedResults));
      }
    }
  }
}
