package com.example.presentation.shop.single.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.Coupon
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.Urls
import com.example.presentation.R
import com.example.presentation.core.theme.AppThemeWithBackground

@Composable
fun ShopActionView(
    shop: Shop,
    onClickAddress: (Shop) -> Unit,
    onClickWebLink: (Urls) -> Unit,
    onClickCoupon: (Coupon.Url) -> Unit,
    onClickShare: (Urls) -> Unit,
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.width(horizontalPadding))
        ActionButton(
            Icons.Outlined.Place,
            label = context.getString(R.string.shop_single_action_place),
            onClick = { onClickAddress(shop) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        ActionButton(
            Icons.Outlined.Public,
            label = context.getString(R.string.shop_single_action_link),
            onClick = { onClickWebLink(shop.urls) }
        )
        when (val coupon = shop.coupon) {
            is Coupon.Url -> {
                Spacer(modifier = Modifier.width(16.dp))
                ActionButton(
                    Icons.Outlined.Payments,
                    label = context.getString(R.string.shop_single_action_coupon),
                    onClick = { onClickCoupon(coupon) }
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        ActionButton(
            Icons.Outlined.Share,
            label = context.getString(R.string.shop_single_action_share),
            onClick = { onClickShare(shop.urls) }
        )
        Spacer(modifier = Modifier.width(horizontalPadding))
    }
}

@Composable
fun ActionButton(
    imageVector: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Box {
        OutlinedButton(
            onClick = onClick,
            shape = CircleShape,
            border = BorderStroke(
                1.dp,
                MaterialTheme.colors.onBackground.copy(alpha = 0.03f),
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.03f),
                contentColor = MaterialTheme.colors.onBackground.copy(alpha = 0.03f),
            ),
            modifier = Modifier.size(60.dp)
        ) {}
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector,
                contentDescription = "Action Icon",
                tint = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                modifier = Modifier
                    .padding(0.dp)
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                fontSize = 9.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopActionView() {
    AppThemeWithBackground {
        ShopActionView(
            shop = fakeSearchResult().shops.first(),
            onClickAddress = {},
            onClickWebLink = {},
            onClickCoupon = {},
            onClickShare = {},
        )
    }
}
