package com.lecheng.hello.ant_bms.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.lecheng.hello.ant_bms.R;

public class MyToast {
    private static Toast toastStart;
    private ImageView ivIcon;
    private TextView tvMsg;

    public MyToast(Context c, String text, int duration) {
        View toastRoot = LayoutInflater.from(c).inflate(R.layout.unit_toast, null);
        this.tvMsg = (TextView) toastRoot.findViewById(R.id.tvMsg);
        this.tvMsg.setText(text);
        if (toastStart == null) {
            toastStart = new Toast(c);
            toastStart.setGravity(48, 0, (((WindowManager) c.getSystemService("window")).getDefaultDisplay().getHeight() * 1) / 6);
            toastStart.setDuration(duration);
            toastStart.setView(toastRoot);
        } else {
            toastStart.setDuration(duration);
            toastStart.setView(toastRoot);
        }
        toastStart.show();
    }
}
