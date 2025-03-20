package com.nakivo.helpers;

import java.io.File;

public final class SystemHelpers {
    public SystemHelpers() {
        super();
    }

    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }
}