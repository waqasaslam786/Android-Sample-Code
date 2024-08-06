package com.example.app_name.util;

import android.app.ProgressDialog;

/**
 *
 */
class Utils {
    public void progress(ProgressDialog progressDialog, boolean isShow) {
        progressDialog.setTitle("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        if (isShow)
            progressDialog.show();
        else
            progressDialog.dismiss();
    }
}
