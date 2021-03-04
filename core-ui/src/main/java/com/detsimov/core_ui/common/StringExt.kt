package com.detsimov.core_ui.common


fun String.clearPhoneMask(): String {
    val symbols = "+()- "
    return filterNot { symbols.indexOf(it) > -1 }
}