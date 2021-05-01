package com.detsimov.leakchecker.ui_android.common

import android.content.Context
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.detsimov.leakchecker.ui_android.R

object AlertDialogFactory {

    fun linkAlertDialog(context: Context, message: Int) = AlertDialog.Builder(context).apply {
        setView(LayoutInflater.from(context).inflate(R.layout.dialog_alert_link, null, false)
            .apply {
                findViewById<TextView>(R.id.tvMessage).apply {
                    movementMethod = LinkMovementMethod.getInstance()
                    text = Html.fromHtml(context.getString(message))
                }
            })
    }
}