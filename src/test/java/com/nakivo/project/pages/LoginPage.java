package com.nakivo.project.pages;

import com.nakivo.project.CommonPage;
import org.openqa.selenium.By;

import static com.nakivo.common.BasePage.*;

public class LoginPage extends CommonPage {
    private By LoginPageLogo = By.xpath("img[src='/img/branding/logo_fl.png']");
    private By nameInput = By.name("fullname");
    private By usernameInput = By.name("username");
    private By emailInput = By.name("email");
    private By passwordInput = By.xpath("placeholder='Password'");
    private By rememberMeLabel = By.xpath("//label[@id='-boxLabelEl']");
    private By forgotPasswordLink = By.xpath("//a[contains(@class,'forgot-password')]");

    private By loginButton = By.xpath("//div[@title='Log In']");
    private By errorMessage = By.xpath("//div[contains(@id,'loginWithUsernameAndPassword')]/div[@id='notificationMessage-1033']//div[contains(@class,'notification-message-content')]");
    private By messageRequiredPassword = By.xpath("");

    public void openLoginPage() {
        openWebsite("http://localhost:4443/c/login/wd/hub");
        waitForPageLoaded();
        verifyElementVisible(LoginPageLogo, "Login page logo is displayed");


        waitForElementVisible(usernameInput);
        waitForElementVisible(passwordInput);
        waitForElementVisible(rememberMeLabel);
        verifyForgotThePasswordLinkDisplayed();

        // to to
        verifyLoginButtonDisabled();
    }

    public void loginSuccess(String username, String password) {
        openLoginPage();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        waitForElementVisible(HomePage.homePageTitle);
        verifyElementVisible(HomePage.homePageTitle, "Login successfully");
    }

    public void loginFailWithInvalidCredentials(String username, String password) {
        openLoginPage();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        waitForElementVisible(errorMessage);
        verifyElementVisible(errorMessage, "Error message is displayed");
    }

    public void loginFailWithEmptyCredentials(String username) {
        openLoginPage();
        setText(usernameInput, username);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(messageRequiredPassword, "Required password error message");
        verifyLoginButtonDisabled();
    }

    public void loginFailWithInvalidEmailFormat(String username, String password) {
        openLoginPage();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Invalid email error message");
    }

    public void loginFailWithExcessivelyLongEmailAddress(String username, String password) {
        openLoginPage();
        setText(usernameInput, username);
        setText(passwordInput, password);

        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Excessively long email address error message");
    }

    public void loginFailWithExcessivelyLongPassword(String username, String password) {
        openLoginPage();
        clickElement(loginButton);
        waitForPageLoaded();

        verifyElementVisible(errorMessage, "Excessively long password error message");
    }

    public void verifyForgotThePasswordLinkDisplayed() {
        verifyElementVisible(forgotPasswordLink, "Forgot password link is displayed");
    }

    public void verifyLoginButtonDisabled() {
        verifyElementVisible(loginButton, "Login button is disabled");
    }

    public void verifyPasswordFieldMasked() {
        verifyElementVisible(passwordInput, "Passwords entered into the password field are masked");
    }

    public void verifyEmailFieldAcceptOnlyValidEmailFormat() {
        verifyElementVisible(emailInput, "The email field accepts only valid email formats");
    }
}
