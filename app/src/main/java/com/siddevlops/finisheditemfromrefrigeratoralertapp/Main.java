package com.siddevlops.finisheditemfromrefrigeratoralertapp;
import android.app.Application;

import com.onesignal.OneSignal;

/**
 *

public class Main extends Application {
    private static final String ONESIGNAL_APP_ID = "bace17e9-b0a6-4e82-bb4f-42293b2173a2";

    @Override
    public void onCreate() {
        super.onCreate();

        // Uncomment to enable verbose OneSignal logging to debug issues if needed.
        // OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
 */