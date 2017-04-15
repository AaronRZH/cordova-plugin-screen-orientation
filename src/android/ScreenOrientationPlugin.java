package com.aaronrzh.cordova.plugin.screenorientation;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

import android.content.Context;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.SharedPreferences;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.json.JSONException;

import android.util.Log;

public class ScreenOrientationPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "ScreenOrientationPlugin";
    private static final String SP_Key = "orientationType";

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equalsIgnoreCase("orientation")) {
            Log.d(LOG_TAG, "orientation: " + args.getString(0));
            setOrientation(args.getString(0));
            callbackContext.success("OK");
        }
        return true;
    }

    @Override
    protected void pluginInitialize() {
        Activity activity = cordova.getActivity();
        SharedPreferences sp = activity.getSharedPreferences(LOG_TAG, Activity.MODE_PRIVATE);
        String type = sp.getString(SP_Key, null);
        if (type != null) {
            setOrientation(type);
        }
    }

    private void setOrientation(String orientation) {
        Activity activity = cordova.getActivity();
        boolean flag = false;
        if (orientation.equalsIgnoreCase("any")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
            flag = true;
        } else if (orientation.equalsIgnoreCase("portrait")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            flag = true;
        } else if (orientation.equalsIgnoreCase("portrait-primary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            flag = true;
        } else if (orientation.equalsIgnoreCase("portrait-secondary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
            flag = true;
        } else if (orientation.equalsIgnoreCase("landscape")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            flag = true;
        } else if (orientation.equalsIgnoreCase("landscape-primary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            flag = true;
        } else if (orientation.equalsIgnoreCase("landscape-secondary")) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            flag = true;
        }
        if (flag) {
            SharedPreferences sp = activity.getSharedPreferences(LOG_TAG, Activity.MODE_PRIVATE);
            sp.edit().putString(SP_Key, orientation).commit();
        }
    }
}
