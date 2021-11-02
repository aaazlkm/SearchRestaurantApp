package com.example.presentation.shop.list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.core.theme.AppThemeWithBackground

@Composable
fun ShopListErrorView(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
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
                        .size(maxWidth * 0.6f)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onBackground.copy(alpha = 0.04f))
                        .align(Alignment.Center),
                ) {
                    Image(
                        painter = painterResource(R.drawable.image_error),
                        contentDescription = "error image",
                        modifier = Modifier
                            .scale(0.85f)
                            .align(Alignment.Center),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = context.getString(R.string.shop_list_error_title),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.high),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(16.dp))
            BoxWithConstraints {
                Button(
                    onClick = onClickRetry,
                ) {
                    Text(
                        text = context.getString(R.string.error_retry),
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            horizontal = 12.dp
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun ShopListErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.high),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = onClickRetry,
        ) {
            Text(
                text = context.getString(R.string.error_retry),
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopListErrorItem() {
    AppThemeWithBackground {
        ShopListErrorItem(
            message = "ネットワークにつながっていません",
            onClickRetry = {}
        )
    }
}
