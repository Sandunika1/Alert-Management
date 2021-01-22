package com.bst.pageObjects.alertManagement;

import com.bst.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AlertManagementUploadWindowPage extends BasePage {
  @FindBy(css = ".file-upload-btn")
  public WebElement uploadFileButton;

  @FindBy(css = "button.primary-btn .mat-button-wrapper")
  public WebElement runButton;

  @FindBy(css = ".mat-slider-horizontal.mat-slider-thumb-label-showing")
  public WebElement confidenceSlider;

  @FindBy(css = "h2.upload-file")
  public WebElement fileUploadTitle;

  public AlertManagementUploadWindowPage(WebDriver driver) {
    super(driver);
  }

  public void doubleClickOnRunButton() {
    doubleClick(runButton);
  }

  public void selectConfidenceLevel(String confidenceLevel) {
    js.setAttribute(confidenceSlider, "aria-valuenow", confidenceLevel);
  }
}
