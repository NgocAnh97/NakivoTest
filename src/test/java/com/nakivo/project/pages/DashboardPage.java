package com.nakivo.project.pages;

import com.nakivo.common.BasePage;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {
    public static By dashboardContainer = By.xpath("//div[contains(@class,'dashboardCenterNormal')]");
    public static By dashboardPageMenu = By.xpath("//div[contains(@id,'navigationView')]");

    public static void verifyDashboardItemsDisplayed() {
        verifyElementVisible(dashboardContainer, "Dashboard container is displayed");
        verifyElementVisible(dashboardPageMenu, "Dashboard page menu is displayed");
    }
}
