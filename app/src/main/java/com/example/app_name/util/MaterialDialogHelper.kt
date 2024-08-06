package com.example.app_name.util

import android.content.Context
import android.view.WindowManager

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.app_name.R

class MaterialDialogHelper {

    fun showSimpleProgress(callingClassContext: Context): MaterialDialog {
        return MaterialDialog(callingClassContext).show {
            customView(
                viewRes = R.layout.custom_progressbar,
                noVerticalPadding = true, dialogWrapContent = true
            )
            cancelOnTouchOutside(false)
            cancelable(false)
            window?.setBackgroundDrawableResource(R.drawable.progress_dialog_rounded_bg)
            val layoutWidth = 300
            window?.setLayout(
                layoutWidth, WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
    }

}
