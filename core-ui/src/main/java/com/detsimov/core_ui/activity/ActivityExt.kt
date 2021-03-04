package com.detsimov.core_ui.activity


import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Activity.setFullScreenMode(){
    window?.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

fun Activity.resetFullScreenMode(){
    window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()

fun Activity.toast(@StringRes string: Int, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, string, length).show()

fun Activity.snack(view: View, @StringRes string: Int, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view, string, length).show()

fun Activity.snack(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view, message, length).show()