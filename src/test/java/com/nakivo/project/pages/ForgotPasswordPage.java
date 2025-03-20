package com.nakivo.project.pages;

import com.nakivo.project.CommonPage;
import org.openqa.selenium.By;

import static com.nakivo.common.BasePage.*;

public class ForgotPasswordPage extends CommonPage {
    private static final By forgotPasswordForm = By.xpath("//div[contains(@id,'loginResetView')]");
    private static final By usernameInput = By.name("email");
    private static final By doneButton = By.cssSelector("div[title='Done']");
    private static final By errorMessage = By.xpath("//div[@id='notificationMessage-1049']//div[contains(@class,'notification-message-content')]");
    private static final By securityInput = By.cssSelector("input[placeholder='Enter security string']");

    public void verifyForgotThePasswordFormDisplayed() {
        verifyElementVisible(forgotPasswordForm, "Forgot password link is displayed");
    }

    public void verifyForgotThePasswordLinkSuccess(String username) {
        verifyForgotThePasswordFormDisplayed();
        setText(usernameInput, username);

        clickElement(doneButton);
        waitForPageLoaded();

        verifySecurityString();
        verifyDoneButtonDisabled();
    }

    private void verifySecurityString() {
        verifyElementVisible(securityInput, "Security input displayed");
    }

    public void verifyDoneButtonDisabled() {
        verifyElementPropertyValue(doneButton, "disabled", "true");
    }

    public void verifyForgotPasswordFailWithInvalidInfo(String username) {
        verifyForgotThePasswordFormDisplayed();
        setText(usernameInput, username);

        clickElement(doneButton);
        waitForPageLoaded();

        verifyElementText(errorMessage, "Cannot find user");
    }
}
