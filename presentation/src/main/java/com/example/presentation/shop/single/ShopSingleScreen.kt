package com.example.presentation.shop.single

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.core.result.LoadResult
import com.example.domain.shop.model.Coupon
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.model.Urls
import com.example.presentation.AppThemeWithBackground
import com.example.presentation.R
import com.example.presentation.core.getReadableMessage
import com.example.presentation.core.intent.openMapApp
import com.example.presentation.core.intent.openShareText
import com.example.presentation.core.intent.openWebApp
import com.example.presentation.core.use
import com.example.presentation.shop.single.view.ShopView
import com.example.presentation.shop.single.viewmodel.ShopSingleViewModel
import com.example.presentation.shop.single.viewmodel.shopSingleViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun ShopSingleScreen(
    shopId: ShopId,
) {
    val context = LocalContext.current
    val (
        state,
        effectFlow,
        dispatch,
    ) = use(shopSingleViewModel())

    LaunchedEffect(key1 = effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                ShopSingleViewModel.Effect.Init -> dispatch(
                    ShopSingleViewModel.Event.SearchShop(
                        shopId
                    )
                )
            }
        }
    }

    Scaffold(
        content = {
            Box {
                ShopSingleScreen(
                    shopLoadResult = state.shopLoadResult,
                    onClickRetry = { dispatch(ShopSingleViewModel.Event.RetrySearchShop(shopId)) },
                    onClickAddress = { openMapApp(context, it.location, it.name) },
                    onClickWebLink = { openWebApp(context, it.pc) },
                    onClickCoupon = { openWebApp(context, it.sp) },
                    onClickShare = { openShareText(context, it.pc) }
                )
            }
        }
    )
}

@Composable
fun ShopSingleScreen(
    shopLoadResult: LoadResult<Shop>,
    onClickRetry: () -> Unit,
    onClickAddress: (Shop) -> Unit,
    onClickWebLink: (Urls) -> Unit,
    onClickCoupon: (Coupon.Url) -> Unit,
    onClickShare: (Urls) -> Unit,
) {
    when (shopLoadResult) {
        LoadResult.Loading -> LoadingView()
        is LoadResult.Success -> ShopView(
            shop = shopLoadResult.value,
            onClickAddress = onClickAddress,
            onClickWebLink = onClickWebLink,
            onClickCoupon = onClickCoupon,
            onClickShare = onClickShare,
        )
        is LoadResult.Error -> ErrorView(
            message = shopLoadResult.e.getReadableMessage(LocalContext.current),
            onClickRetry = onClickRetry
        )
    }
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = message)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onClickRetry
            ) {
                Text(text = context.getString(R.string.error_retry))
            }
        }
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingShopSingleScreen() {
    AppThemeWithBackground {
        ShopSingleScreen(
            shopLoadResult = LoadResult.Success(fakeSearchResult().shops.first()),
            onClickRetry = {},
            onClickAddress = {},
            onClickWebLink = {},
            onClickCoupon = {},
            onClickShare = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSuccessShopSingleScreen() {
    AppThemeWithBackground {
        ShopSingleScreen(
            shopLoadResult = LoadResult.Success(fakeSearchResult().shops.first()),
            onClickRetry = {},
            onClickAddress = {},
            onClickWebLink = {},
            onClickCoupon = {},
            onClickShare = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorShopSingleScreen() {
    AppThemeWithBackground {
        ShopSingleScreen(
            shopLoadResult = LoadResult.Error(Exception()),
            onClickRetry = {},
            onClickAddress = {},
            onClickWebLink = {},
            onClickCoupon = {},
            onClickShare = {},
        )
    }
}
