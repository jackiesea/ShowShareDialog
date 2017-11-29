package com.mr.csh.showsharedialog;

import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by caoshuang on 2017/11/29.
 */

public class Utils {
    public static void setDialogAttributes(Dialog dialog, int width, int heigh, int gravity) {
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(gravity);
        lp.width = width;
        lp.height = heigh;
        dialogWindow.setAttributes(lp);
    }
}
