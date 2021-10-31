package com.example.presentation.core.string

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.domain.shop.model.SearchRange
import com.example.presentation.R

@Composable
fun SearchRange.getString(): String {
    val context = LocalContext.current
    return when (this) {
        SearchRange.L_300 -> context.getString(R.string.search_range_300)
        SearchRange.L_500 -> context.getString(R.string.search_range_500)
        SearchRange.L_1000 -> context.getString(R.string.search_range_1000)
        SearchRange.L_2000 -> context.getString(R.string.search_range_2000)
        SearchRange.L_3000 -> context.getString(R.string.search_range_3000)
    }
}
