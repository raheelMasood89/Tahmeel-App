package com.example.tahmeel.util

import android.view.View

object Utils {
    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

}