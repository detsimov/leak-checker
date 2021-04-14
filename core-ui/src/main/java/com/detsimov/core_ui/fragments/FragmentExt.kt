package com.detsimov.core_ui.fragments

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, length).show()

fun Fragment.toast(@StringRes string: Int, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, string, length).show()

fun Fragment.snack(view: View, @StringRes string: Int, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view, string, length).show()

fun Fragment.snack(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view, message, length)


fun DialogFragment.showOptimize(fragmentManager: FragmentManager, tag: String?) {
    if (fragmentManager.findFragmentByTag(tag) == null) {
        show(fragmentManager, tag)
    }
}

fun Fragment.log(text: String) {
    Log.i(this::class.java.simpleName, text)
    Log.i("Fragments", "${this::class.java.simpleName}: $text")
}

/**
 * @param T Листенер
 * @return Должен вернуть листенер если таковой есть у targetFragment
 */
inline fun <reified T> Fragment.findListenerByTargetFragment(): T? {
    return if (T::class.java.isInstance(targetFragment)) targetFragment as T? else null
}

/**
 * @param T Листенер
 * @return Должен вернуть листенер если таковой есть у parentFragment
 */
inline fun <reified T> Fragment.findListenerByParent(): T? {
    var fragment = this
    while (fragment.parentFragment != null) {
        fragment = fragment.requireParentFragment()
        if ((T::class.java.isInstance(fragment))) {
            return fragment as T
        }
    }
    return if (T::class.java.isInstance(activity)) activity as T? else null
}

/**
 * @param T Листенер
 * @return [findListenerByTargetFragment] или [findListenerByParent]
 * @throws IllegalStateException Если листенер не удалось найти
 */
@Throws(IllegalStateException::class)
inline fun <reified T> Fragment.requireListener(): T {
    return findListenerByTargetFragment()
        ?: findListenerByParent()
        ?: throw IllegalStateException("No targetFragment, neither parentFragment (or activity) implements listener ${T::class.java.simpleName}")
}