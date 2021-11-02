package com.example.presentation.shop.list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.Shop
import com.example.presentation.core.theme.AppThemeWithBackground

private val shopItemHeight = 124.dp

@Composable
fun ShopItem(
    modifier: Modifier = Modifier,
    shop: Shop,
    onClick: (Shop) -> Unit,
) {
    Card(
        elevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick(shop) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Image(
                painter = rememberImagePainter(
                    data = shop.photo.mobile.l,
                ),
                contentDescription = "shop image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(shopItemHeight),
            )
            Column(
                modifier = Modifier
                    .height(shopItemHeight)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start),
                ) {
                    Text(
                        text = shop.genre.name,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = shop.name,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.high),
                        fontWeight = FontWeight.Bold,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        Icons.Outlined.Place,
                        contentDescription = "Place icon",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                        modifier = Modifier
                            .padding(0.dp)
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = shop.mobileAccess,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
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
