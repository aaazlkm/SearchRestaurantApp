package com.example.presentation.shop.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.example.domain.shop.model.Shop
import com.example.presentation.AppThemeWithBackground
import com.example.presentation.R
import com.example.presentation.core.use
import com.example.presentation.shop.list.view.ShopList
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
        topBar = { TopAppBar(title = { Text("") }) },
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
fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.clickable { onClickRetry.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Text(text = context.getString(R.string.error_retry))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedScreen() {
    AppThemeWithBackground {
        ShopListScreen(
            onClickShopItem = {}
        )
    }
}
