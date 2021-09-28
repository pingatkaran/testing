package com.app.techalchemy.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.intuit.sdp.BuildConfig

/*
Use :
 preferences.putString(
                PREFERENCES_ACCESS_TOKEN_KEY, result.data!!.getStringExtra(
                    PREFERENCES_ACCESS_TOKEN_KEY
                )!!
            )

*/


class Preferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE)

    fun putString(key: String, value: String) {
        preferences.edit().apply {
            putString(key, value)
            apply()
        }
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    fun putLong(key: String, value: Long) {
        preferences.edit().apply {
            putLong(key, value)
            apply()
        }
    }

    fun getString(key: String): String = preferences.getString(key, PREFERENCES_DEFAULT_STRING)!!

    fun getBoolean(key: String): Boolean = preferences.getBoolean(key, false)

    fun getLong(key: String): Long = preferences.getLong(key, 0)

    companion object {
        const val PREFERENCE_FILE_KEY = BuildConfig.APPLICATION_ID + ".SHARED_PREFERENCES_KEY"
        const val PREFERENCES_DEFAULT_STRING = "preferencesDefaultString"

        const val USER_ID = "user_id"
    }
}
