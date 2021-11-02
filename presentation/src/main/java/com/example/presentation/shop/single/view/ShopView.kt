package com.example.presentation.shop.single.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.Coupon
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.Urls
import com.example.presentation.core.theme.AppThemeWithBackground

val horizontalPadding = 28.dp

@Composable
fun ShopView(
    shop: Shop,
    onClickBackButton: () -> Unit,
    onClickAddress: (Shop) -> Unit,
    onClickWebLink: (Urls) -> Unit,
    onClickCoupon: (Coupon.Url) -> Unit,
    onClickShare: (Urls) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ShopAppBar(
            onClickBackButton = onClickBackButton,
        )
        Image(
            painter = rememberImagePainter(
                data = shop.photo.mobile.l,
            ),
            contentDescription = "shop photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.618f),
        )
        Spacer(modifier = Modifier.height(24.dp))
        ShopHeaderView(shop = shop)
        Spacer(modifier = Modifier.height(24.dp))
        ShopViewDivider()
        Spacer(modifier = Modifier.height(16.dp))
        ShopActionView(
            shop = shop,
            onClickAddress = onClickAddress,
            onClickWebLink = onClickWebLink,
            onClickCoupon = onClickCoupon,
            onClickShare = onClickShare,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShopViewDivider()
        Spacer(modifier = Modifier.height(16.dp))
        ShopInfoView(
            shop = shop
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
private fun ShopViewDivider() {
    Divider(color = MaterialTheme.colors.onBackground.copy(alpha = 0.05f))
}

@Preview(showBackground = true)
@Composable
fun PreviewShopView() {
    AppThemeWithBackground {
        ShopView(
            shop = fakeSearchResult().shops.first(),
            onClickBackButton = {},
            onClickAddress = {},
            onClickWebLink = {},
            onClickCoupon = {},
            onClickShare = {},
        )
    }
}
