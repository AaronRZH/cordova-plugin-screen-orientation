package com.aaronrzh.cordova.plugin.screenorientation;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

import android.content.Context;
import android.app.Activity;
import android.content.pm.ActivityInfo;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.json.JSONException;

import android.util.Log;

public class ScreenOrientationPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "ScreenOrientationPlugin";

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equalsIgnoreCase("orientation")) {
            Log.d(LOG_TAG, "orientation: " + args.getString(0));
            setOrientation(args.getString(0));
            callbackContext.success("OK");
        }
        return true;
    }

    private void setOrientation(String orientation) {
        Activity activity = cordova.getActivity();
        if (orientation.equalsIgnoreCase("any")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else if (orientation.equalsIgnoreCase("portrait")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        } else if (orientation.equalsIgnoreCase("portrait-primary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (orientation.equalsIgnoreCase("portrait-secondary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        } else if (orientation.equalsIgnoreCase("landscape")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else if (orientation.equalsIgnoreCase("landscape-primary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (orientation.equalsIgnoreCase("landscape-secondary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        }
    }
}
