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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.Shop
import com.example.presentation.R
import com.example.presentation.core.theme.AppThemeWithBackground

@Composable
fun ShopInfoView(shop: Shop) {
    val context = LocalContext.current
    Column {
        Text(
            text = context.getString(R.string.shop_single_info),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = horizontalPadding)
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (shop.name.isNotBlank()) {
            ShopInfoItem(label = context.getString(R.string.shop_single_info_name), shop.name)
        }
        if (shop.mobileAccess.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_access),
                content = shop.mobileAccess
            )
        }
        if (shop.open.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_open),
                content = shop.open
            )
        }
        if (shop.close.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_close),
                content = shop.close
            )
        }
        if (shop.budget.name.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_budget),
                content = "${shop.budget.name}${if (shop.budgetMemo.isNotBlank()) "\n*${shop.budgetMemo}" else ""}"
            )
        }
        if (shop.capacity.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_capacity),
                content = shop.capacity
            )
        }
        if (shop.stationName.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_stationName),
                content = shop.stationName
            )
        }
        if (shop.address.isNotBlank()) {
            ShopInfoItem(
                label = context.getString(R.string.shop_single_info_address),
                content = shop.address
            )
        }
    }
}

@Composable
fun ShopInfoItem(
    label: String,

    content: String,
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = 8.dp, horizontal = horizontalPadding
            )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopInfoView() {
    AppThemeWithBackground {
        ShopInfoView(
            shop = fakeSearchResult().shops.first(),
        )
    }
}
