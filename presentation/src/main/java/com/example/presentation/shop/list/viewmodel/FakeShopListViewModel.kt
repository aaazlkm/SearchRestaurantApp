package com.example.presentation.shop.list.viewmodel

import androidx.paging.PagingData
import com.example.domain.core.fake.fakeSearchResult
import com.example.domain.core.result.LoadResult
import com.example.domain.shop.model.SearchResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.meta.Exhaustive
import kotlin.coroutines.CoroutineContext

fun fakeShopListViewModel(): FakeShopListViewModel {
    return FakeShopListViewModel()
}

class FakeShopListViewModel() : ShopListViewModel {

    private val coroutineScope = CoroutineScope(
        object : CoroutineDispatcher() {
            // for preview
            override fun dispatch(context: CoroutineContext, block: Runnable) {
                block.run()
            }
        }
    )

    private val effectChannel = Channel<ShopListViewModel.Effect>(Channel.UNLIMITED)
    override val effect: Flow<ShopListViewModel.Effect> = effectChannel.receiveAsFlow()

    private val _searchResult: MutableStateFlow<LoadResult<SearchResult>> = MutableStateFlow(
        LoadResult.Success(fakeSearchResult())
    )
    private val searchResult: StateFlow<LoadResult<SearchResult>> = _searchResult

    override val state: StateFlow<ShopListViewModel.State> =
        searchResult.map {
            ShopListViewModel.State(
                pagindDataFlow = flow { PagingData.from(fakeSearchResult().shops) }
            )
        }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Eagerly,
                initialValue = ShopListViewModel.State()
            )

    override fun event(event: ShopListViewModel.Event) {
        coroutineScope.launch {
            @Exhaustive
            when (event) {
            }
        }
    }
}
