/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package com.nakivo.constants;

import com.nakivo.helpers.PropertiesHelpers;
import com.nakivo.helpers.SystemHelpers;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static final int WAIT_PAGE_LOADED = Integer.parseInt(PropertiesHelpers.getValue("WAIT_PAGE_LOADED"));
    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
}
