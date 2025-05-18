package com.nakivo.project.testcases;

import com.nakivo.common.BaseTest;
import com.nakivo.project.CommonPage;
import org.testng.annotations.Test;

public class LandingTest extends BaseTest {
    CommonPage commonPage;

    public LandingTest() {
        commonPage = new CommonPage();
    }

    @Test
    public void Landing_1_Load_Successfully() {
        commonPage.getLandingPage().openLandingPageLoadSuccessfully();
    }
}
