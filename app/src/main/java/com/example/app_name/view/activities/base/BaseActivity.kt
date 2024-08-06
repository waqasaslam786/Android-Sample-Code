package com.example.app_name.view.activities.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.app_name.util.AlertMessageDialog

open class BaseActivity : AppCompatActivity() {

    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun showAlertDialog(
        msg: String,
        positiveButtonCallback: AlertMessageDialog.PositiveButtonCallback? = null
    ) {
        AlertMessageDialog.newInstance(msg, positiveButtonCallback)
            .show(this.supportFragmentManager, AlertMessageDialog.TAG)
    }

}