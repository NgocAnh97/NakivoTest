package com.nakivo.project.testcases;

import com.nakivo.common.BaseTest;
import com.nakivo.project.CommonPage;
import com.nakivo.project.pages.TestPage;
import org.testng.annotations.Test;

import static com.nakivo.common.BasePage.openWebsite;
import static com.nakivo.common.BasePage.waitForPageLoaded;

public class ActiveUrlTest extends BaseTest {
    CommonPage commonPage;

    public ActiveUrlTest() {
        commonPage = new CommonPage();
    }

    @Test
    public void Test_Check_Url() {
        openWebsite("https://nakivo.com/");
        waitForPageLoaded();

        TestPage testPage = new TestPage();
        testPage.checkUrlActiveInPage();
    }
}
