package com.rigby.pushit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

public class Session implements Serializable {

    private static final String SHARED_PREFERENCES_NAME = "SESSION";

    public enum Key {
        TOKEN, USER_ID, EMAIL, PASSWORD
    }


    public static String getString(Context context, Key key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key.toString(), null);
    }

    public static void putString(Activity activity, Key key, String value) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key.toString(), value);
        editor.apply();
    }

    public static void removeString(Activity activity, Key key) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(key.toString());
        editor.apply();
    }


}