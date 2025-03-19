package com.nakivo.project;

import com.nakivo.project.pages.HomePage;
import com.nakivo.project.pages.LoginPage;


public class CommonPage {
    private LoginPage loginPage;
    private HomePage homePage;


    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
}
