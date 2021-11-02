package com.example.presentation.shop.single

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.core.result.LoadResult
import com.example.domain.shop.model.Coupon
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.model.Urls
import com.example.presentation.core.getReadableMessage
import com.example.presentation.core.intent.openMapApp
import com.example.presentation.core.intent.openShareText
import com.example.presentation.core.intent.openWebApp
import com.example.presentation.core.theme.AppThemeWithBackground
import com.example.presentation.core.use
import com.example.presentation.shop.single.view.ShopErrorView
import com.example.presentation.shop.single.view.ShopLoadingView
import com.example.presentation.shop.single.view.ShopView
import com.example.presentation.shop.single.viewmodel.ShopSingleViewModel
import com.example.presentation.shop.single.viewmodel.shopSingleViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun ShopSingleScreen(
    shopId: ShopId,
    onClickBackButton: () -> Unit,
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
                    onClickBackButton = onClickBackButton,
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
    onClickBackButton: () -> Unit,
    onClickRetry: () -> Unit,
    onClickAddress: (Shop) -> Unit,
    onClickWebLink: (Urls) -> Unit,
    onClickCoupon: (Coupon.Url) -> Unit,
    onClickShare: (Urls) -> Unit,
) {
    when (shopLoadResult) {
        LoadResult.Loading -> ShopLoadingView(
            onClickBackButton = onClickBackButton,
        )
        is LoadResult.Success -> ShopView(
            shop = shopLoadResult.value,
            onClickBackButton = onClickBackButton,
            onClickAddress = onClickAddress,
            onClickWebLink = onClickWebLink,
            onClickCoupon = onClickCoupon,
            onClickShare = onClickShare,
        )
        is LoadResult.Error -> ShopErrorView(
            message = shopLoadResult.e.getReadableMessage(LocalContext.current),
            onClickBackButton = onClickBackButton,
            onClickRetry = onClickRetry
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingShopSingleScreen() {
    AppThemeWithBackground {
        ShopSingleScreen(
            shopLoadResult = LoadResult.Loading,
            onClickBackButton = {},
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
            onClickBackButton = {},
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
            onClickBackButton = {},
            onClickRetry = {},
            onClickAddress = {},
            onClickWebLink = {},
            onClickCoupon = {},
            onClickShare = {},
        )
    }
}
