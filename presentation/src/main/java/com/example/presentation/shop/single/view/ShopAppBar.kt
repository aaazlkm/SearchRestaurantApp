package com.example.presentation.shop.single.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun ShopAppBar(
    onClickBackButton: () -> Unit,
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onClickBackButton) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back screen")
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}
