package com.example.presentation.shop.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.Shop
import com.example.domain.shop.usecase.ShopUseCase
import com.example.presentation.shop.list.ShopSource
import com.example.presentation.shop.list.model.SearchQueryBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.meta.Exhaustive
import javax.inject.Inject

@HiltViewModel
class RealShopListViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase,
) : ViewModel(), ShopListViewModel {

    private val _effect = Channel<ShopListViewModel.Effect>(Channel.UNLIMITED)
    override val effectFlow: Flow<ShopListViewModel.Effect> = _effect.receiveAsFlow()

    private val _searchQueryBuilder: MutableStateFlow<SearchQueryBuilder> =
        MutableStateFlow(SearchQueryBuilder.from(SearchQuery()))

    private val _searchQuery: MutableStateFlow<SearchQuery> =
        MutableStateFlow(SearchQuery())

    private val _shopPagingDataFlow: Flow<Flow<PagingData<Shop>>> =
        _searchQuery.map { searchQuery ->
            Pager(PagingConfig(pageSize = ShopSource.PAGE_SIZE)) {
                ShopSource(shopUseCase, searchQuery)
            }.flow.cachedIn(viewModelScope)
        }

    override val state: StateFlow<ShopListViewModel.State> =
        combine(
            _searchQueryBuilder,
            _searchQuery,
            _shopPagingDataFlow,
        ) { searchQueryBuilder, searchQuery, shopPagingDataFlow ->
            ShopListViewModel.State(
                searchQueryBuilder = searchQueryBuilder,
                searchQuery = searchQuery,
                shopPagindDataFlow = shopPagingDataFlow
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ShopListViewModel.State())

    override fun event(event: ShopListViewModel.Event) {
        viewModelScope.launch {
            @Exhaustive
            when (event) {
                is ShopListViewModel.Event.ChangeSearchRange -> {
                    _searchQueryBuilder.value =
                        _searchQueryBuilder.value.setSearchRange(event.searchRange)
                }
                ShopListViewModel.Event.ClickSearchButton -> {
                    val searchQuery = _searchQueryBuilder.value.build()
                    // TODO 現在地取得
                    // 取得できなければ、effectで伝える
                    _searchQuery.value = searchQuery
                }
            }
        }
    }
}
