package com.nakivo.project.pages;

import com.nakivo.constants.LocalizationKeys;
import com.nakivo.project.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.nakivo.common.BasePage.*;

public class LandingPage extends CommonPage {
    private static final By landingPageLogo = By.cssSelector("a.header__logo");
    private static final By switchLangButton = By.cssSelector("select.header__select");

    public void openLandingPageLoadSuccessfully() {
        openWebsite("https://www.nakivo.com/");
        waitForPageLoaded();
        verifyElementVisible(landingPageLogo, "Login page logo is displayed");
    }

    public void switchLanguage(String langCode) {
        new Select(Objects.requireNonNull(waitForElementVisible(switchLangButton)))
                .selectByVisibleText(langCode.toUpperCase());
    }

    private Map<String, By> locatorMap() {
        return Map.of(
                LocalizationKeys.HOME_TITLE, By.className(LocalizationKeys.HOME_TITLE),
                LocalizationKeys.HOME_TAGLINE, By.className(LocalizationKeys.HOME_TAGLINE),
                LocalizationKeys.DISCOVER_BUTTON, By.cssSelector(LocalizationKeys.DISCOVER_BUTTON)
        );
    }

    public Map<String, Supplier<String>> uiElementMap() {
        Map<String, By> locators = locatorMap();

        return locators.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> () -> getTextElement(e.getValue())
                ));
    }
}
