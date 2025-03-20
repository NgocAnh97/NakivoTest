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
    public void Login_1_Load_Successfully() {
        commonPage.getLoginPage().openLoginPageLoadSuccessfully();
    }

    @Test
    public void Login_2a_LoginSuccessWithValidCredentials() {
        commonPage.getLoginPage().loginSuccess(
                "nakivo", "123456");
    }

    @Test
    public void Login_2b_LoginSuccessWithExcelData() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginSuccess(
                excel.getCellData(1, "username"),
                excel.getCellData(1, "password"));
    }

    @Test
    public void Login_3_LoginFailWithInvalidCredentials() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithInvalidCredentials(
                excel.getCellData(2, "username"),
                excel.getCellData(2, "password"));

        commonPage.getLoginPage().loginFailWithEmptyCredentials(
                excel.getCellData(3, "username"));
    }

    @Test
    public void Login_0_LoginFailWithEdgeCases() {
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(ExcelPath, "Login");
        commonPage.getLoginPage().loginFailWithInvalidEmailFormat(
                excel.getCellData(4, "username"),
                excel.getCellData(4, "password"));

        commonPage.getLoginPage().loginFailWithExcessivelyLongEmailAddress(
                excel.getCellData(5, "username"),
                excel.getCellData(5, "password"));

        commonPage.getLoginPage().loginFailWithExcessivelyLongPassword(
                excel.getCellData(6, "username"),
                excel.getCellData(6, "password"));
    }

    @Test
    public void Login_5_VerifySecurityValidation() {
        commonPage.getLoginPage().verifyPasswordFieldMasked();
    }
}
