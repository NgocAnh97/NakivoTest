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

import java.time.Duration;

import static com.nakivo.constants.FrameworkConstants.WAIT_PAGE_LOADED;

public class BaseTest {
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
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "Edge" -> new SafariDriver();
            default -> new ChromeDriver();
        };
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }
}

