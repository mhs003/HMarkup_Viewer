package com.mhs.htmlviewer.fs;

import android.content.*;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyString
{
	public static void setInt(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences("APP_SETTING", 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static int getInt(Context context, String str) {
        return context.getSharedPreferences("APP_SETTING", 0).getInt(str, 0);
    }

    public static void setString(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("APP_SETTING", 0).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static String getString(Context context, String str) {
        return context.getSharedPreferences("APP_SETTING", 0).getString(str, null);
    }
}
