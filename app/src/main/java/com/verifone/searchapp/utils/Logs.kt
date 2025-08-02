package com.verifone.searchapp.utils

import android.util.Log
import com.verifone.searchapp.BuildConfig

object Logs {
    private const val COMMON_TAG = "SearchApp"

    /**
     * Logs a debug message with the common tag.
     * @param message The message to log.
     */
    fun d(TAG: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(COMMON_TAG, "$TAG-> $message")
        }
    }

    /**
     * Logs an error message with the common tag and an optional throwable.
     * @param message The message to log.
     * @param throwable An optional Throwable to log the stack trace.
     */
    fun e(TAG: String,message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.e(COMMON_TAG, "$TAG-> $message", throwable)
        } else {
            Log.e(COMMON_TAG, "$TAG-> $message")
        }
    }
}