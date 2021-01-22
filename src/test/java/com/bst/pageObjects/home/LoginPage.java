package com.bst.pageObjects.home;

import com.bst.base.BasePage;
import com.bst.locators.FindByNg;
import lombok.var;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    public WebElement usernameTextBox;

    @FindBy(id = "password")
    public WebElement passwordTextBox;

    @FindBy(id = "signIn")
    public WebElement loginButton;

    @FindBy(css = "#mainLogo")
    public WebElement logoImage;

    @FindByNg(click = "azure_login()")
    public WebElement logInWithAzureButton;

    @FindBy(css = "[value='WASH-QA']")
    public WebElement continueAzureLogInButton;

    @FindBy(css = "[type='email']")
    public WebElement azureEmailTextBox;

    @FindBy(css = "[type='password']")
    public WebElement azurePasswordTextBox;

    @FindBy(css = "[type='submit']")
    public WebElement logInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void logInAs(String user) {
        var username = config.environment().getUser(user).username;
        var password = config.environment().getUser(user).password;
        if (config.environment().isActiveDirectoryUser) {
            logInAsActiveDirectoryUser(username, password);
        } else {
            logInAsStandardUser(username, password);
        }
    }

    private void setUsername(String username) {
        wait.waitForElementToBeVisible(usernameTextBox);
        usernameTextBox.clear();
        usernameTextBox.sendKeys(username);
    }

    private void setPassword(String password) {
        wait.waitForElementToBeVisible(passwordTextBox);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    private void logInAsStandardUser(String username, String password) {
        wait.waitForElementToBeVisible(usernameTextBox);
        setUsername(username);
        setPassword(password);
        loginButton.click();
    }

    private void logInAsActiveDirectoryUser(String email, String password) {
        wait.waitForElementToBeVisible(logInWithAzureButton);
        click(logInWithAzureButton);
        click(continueAzureLogInButton);
        azureEmailTextBox.sendKeys(email);
        logInButton.click();
        wait.waitForElementToBeVisible(azurePasswordTextBox);
        azurePasswordTextBox.sendKeys(password);
        logInButton.click();
    }
}
