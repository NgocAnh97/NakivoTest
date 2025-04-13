package com.nakivo.project.testcases;

import com.nakivo.common.BaseTest;
import com.nakivo.project.CommonPage;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {
    CommonPage commonPage;

    public ForgotPasswordTest() {
        commonPage = new CommonPage();
    }

    @Test
    public void Forgot_1_ForgotPasswordSuccess() {
        commonPage.getLoginPage().clickForgotThePasswordLink();
        commonPage.getForgotPasswordPage().verifyForgotThePasswordFormDisplayed();
        commonPage.getForgotPasswordPage().verifyForgotThePasswordLinkSuccess("nakivo");
    }

    @Test
    public void Forgot_2_ForgotPasswordFailWithInvalidInfo() {
        commonPage.getLoginPage().clickForgotThePasswordLink();
        commonPage.getForgotPasswordPage().verifyForgotThePasswordFormDisplayed();
        commonPage.getForgotPasswordPage().verifyForgotPasswordFailWithInvalidInfo("nakivo32");
    }
}
