package com.nakivo.project.testcases;

import com.nakivo.common.BaseTest;
import com.nakivo.helpers.ExcelHelpers;
import com.nakivo.project.CommonPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private final String ExcelPath = "src/test/resources/data/Login.xlsx";
    CommonPage commonPage;

    public LoginTest() {
        commonPage = new CommonPage();
    }

    @Test
    public void Login_Login() {

        commonPage.getLoginPage().loginSuccess(
                "test@nakivo.com", "12345");
    }

    @Test
    public void Login_LoginSuccess() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginSuccess(
                excel.getCellData(1, "email"),
                excel.getCellData(1, "password"));
    }

    @Test
    public void Login_LoginFailWithInvalidCredentials() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithInvalidCredentials(
                excel.getCellData(2, "email"),
                excel.getCellData(2, "password"));
    }

    @Test
    public void Login_LoginFailWithEmptyCredentials() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithEmptyCredentials(
                excel.getCellData(3, "email"));
    }

    @Test
    public void Login_LoginFailWithInvalidEmailFormat() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithInvalidEmailFormat(
                excel.getCellData(4, "email"),
                excel.getCellData(4, "password"));
    }

    @Test
    public void Login_LoginFailWithExcessivelyLongEmailAddress() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithExcessivelyLongEmailAddress(
                excel.getCellData(5, "email"),
                excel.getCellData(5, "password"));
    }

    @Test
    public void Login_LoginFailWithExcessivelyLongPassword() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithExcessivelyLongPassword(
                excel.getCellData(6, "email"),
                excel.getCellData(6, "password"));
    }

    @Test
    public void Login_VerifySecurityValidation() {
        commonPage.getLoginPage().verifyPasswordFieldMasked();

        commonPage.getLoginPage().verifyEmailFieldAcceptOnlyValidEmailFormat();
    }
}
