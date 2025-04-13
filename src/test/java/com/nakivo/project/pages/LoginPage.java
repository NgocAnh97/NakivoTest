package com.nakivo.project.pages;

import com.nakivo.project.CommonPage;
import org.openqa.selenium.By;

import static com.nakivo.common.BasePage.*;

public class LoginPage extends CommonPage {
    private static final By loginPageLogo = By.cssSelector("img[src='/img/branding/logo_fl.png']");
    private static final By usernameInput = By.name("username");
    private static final By passwordInput = By.cssSelector("input[placeholder='Password']");
    private static final By rememberMeLabel = By.xpath("//label[@id='-boxLabelEl']");
    private static final By forgotPasswordLink = By.xpath("//a[contains(@class,'forgot-password')]");

    private static final By loginButton = By.xpath("//div[@title='Log In']");
    private static final By errorMessage = By.xpath("//div[contains(@id,'loginWithUsernameAndPassword')]" +
            "/div[@id='notificationMessage-1033']//div[contains(@class,'notification-message-content')]");

    public void openLoginPageLoadSuccessfully() {
        openWebsite("https://localhost:4443/c/login");
        waitForPageLoaded();
        verifyElementVisible(loginPageLogo, "Login page logo is displayed ");

        waitForElementVisible(usernameInput);
        waitForElementVisible(passwordInput);
        waitForElementVisible(rememberMeLabel);
        verifyForgotThePasswordLinkDisplayed();

        verifyLoginButtonDisabled();
    }

    public void loginSuccess(String username, String password) {
        openLoginPageLoadSuccessfully();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        DashboardPage.verifyDashboardItemsDisplayed();
    }

    public void loginFailWithInvalidCredentials(String username, String password) {
        openLoginPageLoadSuccessfully();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Error message is displayed");
        verifyLoginErrorMessageText();
    }

    public void loginFailWithEmptyCredentials(String username) {
        openLoginPageLoadSuccessfully();
        setText(usernameInput, username);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Required password error message");
        verifyLoginButtonDisabled();
        verifyLoginErrorMessageText();
    }

    public void loginFailWithInvalidEmailFormat(String username, String password) {
        openLoginPageLoadSuccessfully();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Invalid email error message");
        verifyLoginErrorMessageText();
    }

    public void loginFailWithExcessivelyLongEmailAddress(String username, String password) {
        openLoginPageLoadSuccessfully();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Excessively long email address error message");
        verifyLoginErrorMessageText();
    }

    public void loginFailWithExcessivelyLongPassword(String username, String password) {
        openLoginPageLoadSuccessfully();
        setText(usernameInput, username);
        setText(passwordInput, password);
        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Excessively long password error message");
        verifyLoginErrorMessageText();
    }

    public void verifyPasswordFieldMasked() {
        openLoginPageLoadSuccessfully();
        verifyElementPropertyValue(passwordInput, "type", "password");
    }

    public void verifyLoginErrorMessageText() {
        verifyElementText(errorMessage, "Incorrect credentials.");
    }

    public void verifyForgotThePasswordLinkDisplayed() {
        verifyElementVisible(forgotPasswordLink, "Forgot password link is displayed");
    }

    public void clickForgotThePasswordLink() {
        openLoginPageLoadSuccessfully();
        verifyForgotThePasswordLinkDisplayed();
        clickElement(forgotPasswordLink);
    }

    public void verifyLoginButtonDisabled() {
        verifyElementPropertyValue(loginButton, "disabled", "true");
    }
}
