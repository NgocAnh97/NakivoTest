package com.nakivo.project.pages;

import com.nakivo.project.CommonPage;
import org.openqa.selenium.By;

import static com.nakivo.common.BasePage.*;

public class LoginPage extends CommonPage {
    private By loginPageTitle = By.xpath("//input[@]");
    private By emailInput = By.xpath("//input[@]");
    private By passwordInput = By.xpath("//input[@]");
    private By signInButton = By.xpath("//input[@]");
    private By errorMessage = By.xpath("//input[@]");
    private By messageRequiredPassword = By.xpath("//input[@]");
    private By forgotPasswordLink = By.xpath("//input[@]");

    public void openLoginPage() {
        openWebsite(" https://localhost:4443/c/main");
        clickElement();
        waitForPageLoaded();
        verifyElementVisible(loginPageTitle, "Login page is displayed");
        verifySignInButtonDisabled();
        verifyForgotThePasswordLink();
    }

    public void loginSuccess(String email, String password) {
        openLoginPage();
        sleep(3);
        setText(emailInput, email);
        setText(passwordInput, password);

        clickElement(signInButton);
        waitForPageLoaded();

        waitForElementVisible(HomePage.homePageTitle);
        verifyElementVisible(HomePage.homePageTitle, "Login successfully");
    }

    public void loginFailWithInvalidCredentials(String email, String password) {
        openLoginPage();
        sleep(3);
        setText(emailInput, email);
        setText(passwordInput, password);

        clickElement(signInButton);
        waitForPageLoaded();

        waitForElementVisible(errorMessage);
        verifyElementVisible(errorMessage, "Error message is displayed");
    }

    public void loginFailWithEmptyCredentials(String email) {
        openLoginPage();
        sleep(3);
        setText(emailInput, email);

        clickElement(signInButton);
        waitForPageLoaded();

        verifyElementVisible(messageRequiredPassword, "Required password error message");
        verifySignInButtonDisabled();
    }

    public void loginFailWithInvalidEmailFormat(String email, String password) {
        openLoginPage();
        sleep(3);
        setText(emailInput, email);
        setText(passwordInput, password);

        clickElement(signInButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Invalid email error message");
    }

    public void loginFailWithExcessivelyLongEmailAddress(String email, String password) {
        openLoginPage();
        sleep(3);
        setText(emailInput, email);
        setText(passwordInput, password);

        clickElement(signInButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Excessively long email address error message");
    }

    public void loginFailWithExcessivelyLongPassword(String email, String password) {
        openLoginPage();
        sleep(3);
        setText(emailInput, email);
        setText(passwordInput, password);

        clickElement(signInButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Excessively long password error message");
    }

    public void verifyForgotThePasswordLink() {
        verifyElementVisible(forgotPasswordLink, "Forgot password link is displayed");
    }

    public void verifySignInButtonDisabled() {
        verifyElementVisible(signInButton, "Sign in button is disabled");
    }

    public void verifyPasswordFieldMasked() {
        verifyElementVisible(passwordInput, "Passwords entered into the password field are masked");
    }

    public void verifyEmailFieldAcceptOnlyValidEmailFormat() {
        verifyElementVisible(emailInput, "The email field accepts only valid email formats");
    }
}
