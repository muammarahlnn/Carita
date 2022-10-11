package com.ardnn.carita.ui.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showSnackbar(activity: Activity, message: String) {
    Snackbar.make(
        activity.window.decorView.rootView,
        message,
        Snackbar.LENGTH_SHORT
    ).show()
}