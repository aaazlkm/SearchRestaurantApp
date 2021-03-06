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
import com.example.domain.location.model.Location
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.Shop
import com.example.presentation.core.getReadableMessage
import com.example.presentation.core.theme.AppThemeWithBackground
import com.example.presentation.shop.list.model.EmptyImageType
import com.example.presentation.shop.list.model.SearchState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun ShopList(
    searchQuery: SearchQuery,
    pagingDataFlow: Flow<PagingData<Shop>>,
    emptyImageType: EmptyImageType,
    onClickShopItem: (Shop) -> Unit,
    onClickSearchBar: () -> Unit,
) {
    val lazyPagingItems = pagingDataFlow.collectAsLazyPagingItems()
    LazyColumn {
        item {
            SearchBar(
                searchState = SearchState.Searching(searchQuery, pagingDataFlow),
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
                        ShopListErrorView(
                            message = refresh.error.getReadableMessage(LocalContext.current),
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
            when (val append = loadState.append) {
                is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                is LoadState.Error -> {
                    item {
                        ShopListErrorItem(
                            message = append.error.getReadableMessage(LocalContext.current),
                            onClickRetry = { retry() }
                        )
                    }
                }
                else -> {
                }
            }
            if (loadState.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && lazyPagingItems.itemCount == 0) {
                item {
                    ShopListEmptyView(
                        emptyImageType = emptyImageType,
                        modifier = Modifier.fillParentMaxSize(),
                    )
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
            searchQuery = SearchQuery(Location(latitude = 35.6938, longitude = 139.7034)),
            pagingDataFlow = flow { PagingData.from(fakeSearchResult().shops) },
            emptyImageType = EmptyImageType.CAKE,
            onClickShopItem = {},
            onClickSearchBar = {},
        )
    }
}
