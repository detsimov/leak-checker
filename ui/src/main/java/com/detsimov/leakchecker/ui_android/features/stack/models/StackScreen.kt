package com.detsimov.leakchecker.ui_android.features.stack.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
enum class StackScreen : Parcelable {
    TRACK,
    LEAK,
    SETTINGS
}