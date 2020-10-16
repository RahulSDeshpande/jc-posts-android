package com.rahulografy.jcposts.util.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Show [Toast] message
 */
fun Context.toast(text: String?, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

/**
 * Show [Toast] message
 */
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    context?.toast(text = text, duration = duration)
}

fun View.show(show: Boolean) {
    visibility =
        if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
}