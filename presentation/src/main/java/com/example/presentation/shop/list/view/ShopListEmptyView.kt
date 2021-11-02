package com.example.presentation.shop.list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.core.theme.AppThemeWithBackground
import com.example.presentation.shop.list.model.EmptyImageType
import com.example.presentation.shop.list.model.getDrawable

@Composable
fun ShopListEmptyView(
    emptyImageType: EmptyImageType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            BoxWithConstraints {
                Box(
                    modifier = Modifier
                        .size(this.maxWidth * 0.7f)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onBackground.copy(alpha = 0.04f))
                        .align(Alignment.Center),
                ) {
                    Image(
                        painter = painterResource(emptyImageType.getDrawable()),
                        contentDescription = "empty image",
                        modifier = Modifier
                            .scale(0.80f)
                            .align(Alignment.Center),
                        contentScale = ContentScale.FillWidth

                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = context.getString(R.string.shop_list_empty_title),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.high),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = context.getString(R.string.shop_list_empty_sub_title),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopListEmptyView() {
    AppThemeWithBackground {
        ShopListEmptyView(
            emptyImageType = EmptyImageType.CAKE,
        )
    }
}
