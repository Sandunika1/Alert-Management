package com.bst.pageObjects.home;

import com.bst.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "doamin_risk")
    public WebElement riskTile;

    @FindBy(css = "button i.fa-search")
    public WebElement advancedSearchTile;

    @FindBy(css = "button i.fa-suitcase")
    public WebElement casesTile;

    @FindBy(css = ".fa-bell.icon")
    public WebElement alertManagementTile;

}
