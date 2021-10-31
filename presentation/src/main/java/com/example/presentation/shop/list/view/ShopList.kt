package com.example.presentation.shop.list.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.Shop
import com.example.presentation.AppThemeWithBackground
import com.example.presentation.core.getReadableMessage
import com.example.presentation.shop.list.ErrorItem
import com.example.presentation.shop.list.ErrorView
import com.example.presentation.shop.list.LoadingItem
import com.example.presentation.shop.list.LoadingView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun ShopList(
    searchQuery: SearchQuery,
    pagingDataFlow: Flow<PagingData<Shop>>,
    onClickShopItem: (Shop) -> Unit,
    onClickSearchBar: () -> Unit,
) {
    val lazyPagingItems = pagingDataFlow.collectAsLazyPagingItems()
    LazyColumn {
        item {
            SearchBar(
                searchQuery = searchQuery,
                onClick = onClickSearchBar,
            )
        }
        items(lazyPagingItems) { it?.let { ShopItem(shop = it, onClick = onClickShopItem) } }
        lazyPagingItems.apply {
            when (val refresh = loadState.refresh) {
                is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                is LoadState.Error -> {
                    item {
                        ErrorView(
                            message = refresh.error.getReadableMessage(LocalContext.current),
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                else -> {
                }
            }
            when (val append = loadState.append) {
                is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = append.error.getReadableMessage(LocalContext.current),
                            onClickRetry = { retry() }
                        )
                    }
                }
                else -> {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopList() {
    AppThemeWithBackground {
        ShopList(
            searchQuery = SearchQuery(),
            pagingDataFlow = flow { PagingData.from(fakeSearchResult().shops) },
            onClickShopItem = {},
            onClickSearchBar = {},
        )
    }
}
