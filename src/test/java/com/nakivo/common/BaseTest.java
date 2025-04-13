package com.nakivo.common;

import com.nakivo.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.nakivo.constants.FrameworkConstants.WAIT_PAGE_LOADED;

public class BaseTest {
    public static SoftAssert softAssert = new SoftAssert();

    @Parameters("browser")
    @BeforeMethod
    public void createDriver(@Optional("firefox") String browser) {
        WebDriver driver = getBrowserDriver(browser);

        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_PAGE_LOADED));
    }

    private WebDriver getBrowserDriver(String browser) {
        return switch (browser) {
            case "Firefox" -> new FirefoxDriver();
            case "Edge" -> new SafariDriver();
            default -> new ChromeDriver();
        };
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        stopSoftAssertAll();
        DriverManager.quit();
    }

    public void softAssertEquals(String actual, String expect, String message) {
        softAssert.assertEquals(actual, expect, message);
    }

    public void stopSoftAssertAll() {
        softAssert.assertAll();
    }
}

