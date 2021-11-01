package com.example.presentation.core.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.example.domain.location.model.Location

fun openMapApp(context: Context, location: Location, query: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:${location.latitude},${location.longitude}?q=$query")
    )
    ContextCompat.startActivity(context, intent, null)
}
