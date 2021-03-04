package com.detsimov.core_data

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun SharedPreferences.prefString(name: String, defValue: String = "") = object : ReadWriteProperty<Any?, String> {
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) = edit().putString(name, value).apply()
    override fun getValue(thisRef: Any?, property: KProperty<*>): String = getString(name, defValue) ?: defValue
}

fun SharedPreferences.prefStringNullable(
    name: String,
    defValue: String? = null,
) = object : ReadWriteProperty<Any?, String?> {
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) = edit().putString(name, value).apply()
    override fun getValue(thisRef: Any?, property: KProperty<*>): String? = getString(name, defValue)
}