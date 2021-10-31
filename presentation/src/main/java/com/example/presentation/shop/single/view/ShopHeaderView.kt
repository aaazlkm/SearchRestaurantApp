package com.example.presentation.shop.single.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.Shop
import com.example.presentation.AppThemeWithBackground

@Composable
fun ShopHeaderView(shop: Shop) {
    Column {
        Text(
            text = shop.genre.name,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            modifier = Modifier.padding(horizontal = horizontalPadding)
        )
        Text(
            text = shop.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = shop.catch,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopHeaderView() {
    AppThemeWithBackground {
        ShopHeaderView(
            shop = fakeSearchResult().shops.first(),
        )
    }
}
