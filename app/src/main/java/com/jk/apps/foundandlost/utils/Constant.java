package com.jk.apps.foundandlost.utils;

import android.content.Context;
import android.widget.Toast;

public class Constant {

    public static final int LOST_POST = 0, FOUND_POST = 1;

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
