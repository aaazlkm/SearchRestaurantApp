package com.example.presentation.shop.list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.Shop
import com.example.presentation.AppThemeWithBackground

@Composable
fun ShopItem(
    modifier: Modifier = Modifier,
    shop: Shop,
    onClick: (Shop) -> Unit,
) {
    Row(
        modifier = modifier.clickable { onClick(shop) }
    ) {
        Image(
            painter = rememberImagePainter(
                data = shop.logoImage,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.width(32.dp))
        Text(text = shop.name)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopItem() {
    AppThemeWithBackground {
        ShopItem(
            shop = fakeSearchResult().shops.first(),
            onClick = {}
        )
    }
}
