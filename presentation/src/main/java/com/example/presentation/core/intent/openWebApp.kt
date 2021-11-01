package com.example.presentation.core.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

fun openWebApp(context: Context, url: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    ContextCompat.startActivity(context, intent, null)
}
