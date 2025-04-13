package com.nakivo.project;

import com.nakivo.project.pages.DashboardPage;
import com.nakivo.project.pages.ForgotPasswordPage;
import com.nakivo.project.pages.LandingPage;
import com.nakivo.project.pages.LoginPage;

public class CommonPage {
    private LoginPage loginPage;
    private DashboardPage homePage;
    private ForgotPasswordPage forgotPasswordPage;
    private LandingPage landingPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if (homePage == null) {
            homePage = new DashboardPage();
        }
        return homePage;
    }

    public ForgotPasswordPage getForgotPasswordPage() {
        if (forgotPasswordPage == null) {
            forgotPasswordPage = new ForgotPasswordPage();
        }
        return forgotPasswordPage;
    }

    public LandingPage getLandingPage() {
        if (landingPage == null) {
            landingPage = new LandingPage();
        }
        return landingPage;
    }
}
