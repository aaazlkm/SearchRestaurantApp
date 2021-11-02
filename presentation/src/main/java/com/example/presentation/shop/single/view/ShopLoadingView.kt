package com.example.presentation.shop.single.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.core.theme.AppThemeWithBackground

@Composable
fun ShopLoadingView(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShopAppBar(
                onClickBackButton = onClickBackButton,
            )
            BoxWithConstraints {
                Spacer(modifier = Modifier.height(maxHeight * 0.2f))
            }
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopLoadingView() {
    AppThemeWithBackground {
        ShopLoadingView(
            onClickBackButton = {}
        )
    }
}
