/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package com.nakivo.constants;

import com.nakivo.helpers.PropertiesHelpers;
import com.nakivo.helpers.SystemHelpers;

import java.io.File;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelpers.getValue("EXCEL_DATA_FILE_PATH");
    public static final String JSON_DATA_FILE_PATH = PropertiesHelpers.getValue("JSON_DATA_FILE_PATH");
    public static final String EXCEL_CMS_LOGIN = PropertiesHelpers.getValue("EXCEL_CMS_LOGIN");
    public static final String EXCEL_CMS_DATA = PropertiesHelpers.getValue("EXCEL_CMS_DATA");
    public static final String EXCEL_CMS_PRODUCTS_USER = PropertiesHelpers.getValue("EXCEL_CMS_PRODUCTS_USER");

    public static final String BROWSER = PropertiesHelpers.getValue("BROWSER");
    public static final String URL_CRM = PropertiesHelpers.getValue("URL_CRM");
    public static final String URL_CMS_ADMIN = PropertiesHelpers.getValue("URL_CMS_ADMIN");
    public static final String URL_CMS_USER = PropertiesHelpers.getValue("URL_CMS_USER");
    public static final String REMOTE_URL = PropertiesHelpers.getValue("REMOTE_URL");
    public static final String REMOTE_PORT = PropertiesHelpers.getValue("REMOTE_PORT");
    public static final String PROJECT_NAME = PropertiesHelpers.getValue("PROJECT_NAME");
    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelpers.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelpers.getValue("EXPORT_VIDEO_PATH");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String SEND_REPORT_TO_TELEGRAM = PropertiesHelpers.getValue("SEND_REPORT_TO_TELEGRAM");
    public static final String TELEGRAM_TOKEN = PropertiesHelpers.getValue("TELEGRAM_TOKEN");
    public static final String TELEGRAM_CHATID = PropertiesHelpers.getValue("TELEGRAM_CHATID");
    public static final String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public static final String TARGET = PropertiesHelpers.getValue("TARGET");
}
