package com.xdwin.abstraction.ext

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

fun View.showSoftKeyboard() {
    if (this.requestFocus()) {
        val imm = getSystemService(this.context, InputMethodManager::class.java) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideSoftKeyboard() {
    val imm = getSystemService(this.context, InputMethodManager::class.java) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}