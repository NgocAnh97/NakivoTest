package com.nakivo.localization;

import com.nakivo.common.BaseTest;
import com.nakivo.project.CommonPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.function.Supplier;

public class LandingLocalizationTest extends BaseTest {
    CommonPage commonPage;

    public LandingLocalizationTest() {
        commonPage = new CommonPage();
    }

    @DataProvider(name = "languages")
    public Object[][] languageCodes() {
        return new Object[][]{{"en"}, {"fr"}};
    }

    private void verifyTranslation(Map<String, Supplier<String>> elementMap, Map<String, String> translations) {
        //Using Supplier<String> to delay the execution util needed
        //Each key is mapped to the method that return the UI's actual value
        for (Map.Entry<String, Supplier<String>> entry : elementMap.entrySet()) {
            String key = entry.getKey();
            String actualValue = entry.getValue().get();
            String expectValue = translations.get(key);

            Assert.assertEquals(actualValue, expectValue, "Mismatch for key: " + key);
        }
    }

    @Test(dataProvider = "languages")
    public void verifyLocalization(String langCode) {
        commonPage.getLandingPage().openLandingPageLoadSuccessfully();
        commonPage.getLandingPage().switchLanguage(langCode);
        Map<String, String> translations = LocalizationData.getTranslations(langCode);

        verifyTranslation(commonPage.getLandingPage().uiElementMap(), translations);
    }
}

