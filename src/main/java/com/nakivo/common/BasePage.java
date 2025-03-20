package com.nakivo.common;

import com.nakivo.constants.FrameworkConstants;
import com.nakivo.driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

@Log4j2
public class BasePage {
    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void openWebsite(String URL) {
        DriverManager.getDriver().get(URL);
        waitForPageLoaded();

        log.info("Open website with URL: " + URL);
    }

    public static void setText(By by, String value) {
        Objects.requireNonNull(waitForElementVisible(by)).sendKeys(value);
        log.info("Set text " + value + " on " + by.toString());
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_PAGE_LOADED));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        ExpectedCondition<Boolean> jsLoad = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        };

        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady) {
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                log.error("Timeout waiting for page load. (" + 40 + "s)");
                Assert.fail("Timeout waiting for page load. (" + 40 + "s)");
            }
        }
    }

    public static void clickElement(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
        log.info("Click on the element " + by.toString());
    }

    public static void verifyElementVisible(By by, String message) {
        log.info("Verify element visible " + by);
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            if (message.isEmpty()) {
                log.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                log.error(message + by);
                Assert.fail(message + by);
            }
        }
    }

    public static void verifyElementPropertyValue(By by, String propertyName, String propertyValue) {
        log.info("Verify element property value " + by);
        waitForElementVisible(by);
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT));
            wait.until(ExpectedConditions.domPropertyToBe(waitForElementPresent(by), propertyName, propertyValue));
        } catch (Exception e) {
            log.error("Element: " + by.toString() + ". Not found Property value: "
                    + propertyValue + " with property name: " + propertyName +
                    ". Actual get Property value is: " + getProperty(by, propertyName));
            Assert.fail("Element: " + by.toString() + ". Not found Property value: " + propertyValue +
                    " with property name: " + propertyName + ". Actual get Property value is: "
                    + getProperty(by, propertyName));
        }
    }

    public static String getProperty(By by, String propertyName) {
        return getWebElement(by).getDomProperty(propertyName);
    }

    public static WebElement waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            log.error("Element not exist. (waitForElementPresent) " + by.toString());
            Assert.fail("Element not exist. (waitForElementPresent) " + by.toString());
        }
        return null;
    }

    public static WebElement waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),
                    Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT));

            boolean check = isElementVisible(by, FrameworkConstants.WAIT_EXPLICIT);
            if (!check) {
                scrollToElementAtBottom(by);
            }
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            log.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public static void verifyElementText(By by, String text) {
        if (getTextElement(by).trim().equals(text.trim())) {
            log.info("Verify the element text is as expected " + getTextElement(by));
        } else {
            log.error("The element text is not as expected. Expected: " + text + ", Actual: " + getTextElement(by));
            Assert.fail("The element text is not as expected. Expected: " + text + ", Actual: " + getTextElement(by));
        }
    }

    private static String getTextElement(By by) {
        return Objects.requireNonNull(waitForElementVisible(by)).getText();
    }

    public static boolean isElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void scrollToElementAtBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        log.info("Scroll to element " + by);
    }
}
