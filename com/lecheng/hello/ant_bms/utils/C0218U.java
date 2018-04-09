package com.lecheng.hello.ant_bms.utils;

import android.content.Context;

public class C0218U {
    public static boolean isZh(Context context) {
        if (context.getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            return true;
        }
        return false;
    }
}
