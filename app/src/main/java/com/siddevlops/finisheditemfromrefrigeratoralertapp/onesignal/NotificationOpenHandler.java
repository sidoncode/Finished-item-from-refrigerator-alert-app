package com.siddevlops.finisheditemfromrefrigeratoralertapp.onesignal;

import com.siddevlops.finisheditemfromrefrigeratoralertapp.*;

import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;
/**
class NotificationOpenHandler implements OneSignal.NotificationReceivedHandler {
    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);

            //Intent intent = new Intent(mContext,EatingHabit.class);
            Intent intent = new Intent(getApplicationContext(), (Class<?>) EatingHabit.class);
            // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);

        }
    }

    private String getApplicationContext() {
    }
}
**/