package com.bst.pageObjects.advancedsearch;

import com.bst.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class PersonFormPage extends BasePage {

    public PersonFormPage(WebDriver driver) {
        super(driver);
    }

}
