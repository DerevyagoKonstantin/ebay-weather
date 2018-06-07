package com.ebay.weather.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Activity.showKeyboard() {
    currentFocus?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(it, 0)
    }
}

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }