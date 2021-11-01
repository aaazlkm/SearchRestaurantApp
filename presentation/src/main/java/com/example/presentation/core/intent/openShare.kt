package com.example.presentation.core.intent

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

fun openShareText(context: Context, content: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    ContextCompat.startActivity(context, shareIntent, null)
}
