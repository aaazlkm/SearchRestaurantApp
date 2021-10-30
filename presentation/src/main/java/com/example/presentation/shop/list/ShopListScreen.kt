package com.example.presentation.shop.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.example.domain.shop.model.Shop
import com.example.presentation.AppThemeWithBackground
import com.example.presentation.core.use
import com.example.presentation.shop.list.view.ShopList
import com.example.presentation.shop.list.viewmodel.fakeShopListViewModel
import com.example.presentation.shop.list.viewmodel.provideShopListViewModelFactory
import com.example.presentation.shop.list.viewmodel.shopListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun ShopListScreen(
    onClickShopItem: (Shop) -> Unit,
) {
    val (
        state,
        _,
        _,
    ) = use(shopListViewModel())

    ShopListScreen(
        pagingDataFlow = state.pagindDataFlow,
        onClickShopItem = onClickShopItem,
    )
}

@Composable
fun ShopListScreen(
    pagingDataFlow: Flow<PagingData<Shop>> = flow { PagingData.empty<Shop>() },
    onClickShopItem: (Shop) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("TopAppBar") }) },
        content = {
            Box {
                ShopList(
                    pagingDataFlow = pagingDataFlow,
                    onClickShopItem = onClickShopItem
                )
            }
        }
    )
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading")
            CircularProgressIndicator()
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
) {
    Column(
        modifier = modifier.clickable { onClickRetry.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Text(text = "Retry")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedScreen() {
    AppThemeWithBackground {
        CompositionLocalProvider(
            provideShopListViewModelFactory { fakeShopListViewModel() },
        ) {
            ShopListScreen(
                onClickShopItem = {}
            )
        }
    }
}
