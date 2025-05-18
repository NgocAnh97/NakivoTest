package com.nakivo.project.pages;

import com.nakivo.driver.DriverManager;
import com.nakivo.project.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestPage extends CommonPage {
    public void checkUrlActiveInPage() {
        List<WebElement> links = DriverManager.getDriver().findElements(By.tagName("a"));
        List<String> urlLists = new ArrayList<String>();

        for (WebElement e : links) {
            String url = e.getDomAttribute("href");
            urlLists.add(url);
//            checkBrokenLink(url);
        }
        long startTime = System.currentTimeMillis();
        urlLists.parallelStream().forEach(this::checkBrokenLink);
        long endTime = System.currentTimeMillis();

        System.out.println("Total time: " + (endTime - startTime));
    }

    public void checkBrokenLink(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() >= 400) {
                System.out.println(linkUrl + " ----> " + httpURLConnection.getResponseMessage() + " is broken link");
            } else {
                System.out.println(linkUrl + " ----> " + httpURLConnection.getResponseMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
