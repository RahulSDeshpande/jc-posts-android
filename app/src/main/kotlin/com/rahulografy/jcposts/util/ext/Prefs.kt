package com.rahulografy.jcposts.util.ext

import android.content.SharedPreferences

lateinit var prefs: SharedPreferences

fun SharedPreferences.put(name: String, any: Any) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name, any).apply()
    }
}

fun SharedPreferences.remove(name: String) {
    edit().remove(name).apply()
}

private const val IS_APP_INIT = "IS_APP_INIT"

fun initApp() {
    prefs.put(IS_APP_INIT, true)
}

fun isAppInit() = prefs.getBoolean(IS_APP_INIT, false)