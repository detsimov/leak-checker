package com.detsimov.core_ui.activity

import android.app.Activity
import android.view.WindowManager

fun Activity.setFullScreenMode(){
    window?.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

fun Activity.resetFullScreenMode(){
    window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}
