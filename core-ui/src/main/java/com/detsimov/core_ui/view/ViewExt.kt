package com.detsimov.core_ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun Spinner.setItemSelectedCallback(callback: (position: Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            callback.invoke(position)
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}

fun FloatingActionButton.startProgress(
    color: Int
) {
    isClickable = false
    setImageDrawable(createDefaultProgressDrawable(color, context))
}

fun createDefaultProgressDrawable(color: Int, context: Context) =
    CircularProgressDrawable(context).apply {
        setColorSchemeColors(color)
        strokeWidth = 5f
        centerRadius = 25f
        start()
    }

fun FloatingActionButton.stopProgress(drawable: Drawable?) {
    isClickable = true
    (drawable as? CircularProgressDrawable)?.stop()
    setImageDrawable(drawable)
}

fun Button.startProgress(
    progressDrawable: CircularProgressDrawable = createDefaultProgressDrawable(
        Color.WHITE, context
    )
) {
    setCompoundDrawables(progressDrawable, null, null, null)
}

fun Button.stopProgress() {
    compoundDrawables.forEach {
        if (it is CircularProgressDrawable) it.stop()
    }
    setCompoundDrawables(null, null, null, null)
}
