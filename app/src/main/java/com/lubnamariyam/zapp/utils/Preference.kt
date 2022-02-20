package com.lubnamariyam.zapp.utils

import android.content.Context
import android.content.SharedPreferences

class Preference {
    companion object {
        private val sharedPref = "sharedpreference"
        val FETCH_FROM_DB: String = "FETCH_FROM_DB"

        /**
         * Save preference manager is used to save data in SharedPreference Data
         *
         * @param key
         * @param value
         * @param context
         */
        fun savePreferenceManager(key: String, value: Any, context: Context) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
            when (value) {
                is Boolean -> sharedPreferences.edit().putBoolean(key, value).apply()
                is Int -> sharedPreferences.edit().putInt(key, value).apply()
                is Long -> sharedPreferences.edit().putLong(key, value).apply()
                is Float -> sharedPreferences.edit().putFloat(key, value).apply()
                is String -> sharedPreferences.edit().putString(key, value).apply()
                is Set<*> -> {
                    for ((index, item) in value.withIndex()) {
                        sharedPreferences.edit().putString(key + "_$index", item.toString()).apply()
                    }
                }
                else -> {
                    sharedPreferences.edit().putString(key, value.toString()).apply()
                }
            }
        }

        /**
         * Retrive preference manager is used to get data in SharedPreference, which we stored.
         *
         * @param key
         * @param context
         * @return
         */
        fun retrivePreferenceManager(key: String, context: Context): Any {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
            return if (sharedPreferences.all.containsKey(key)) {
                sharedPreferences.all[key] as Any
            } else {
                key
            }
        }

        /**
         * Clear preference manager is used to clear all data form SharedPreference, which we stored.
         *
         * @param context
         */
        fun clearPreferenceManager(context: Context) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
        }

    }

}