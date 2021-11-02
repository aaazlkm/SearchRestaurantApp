package com.example.presentation.shop.list.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.Shop
import com.example.domain.shop.usecase.ShopUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class SearchState {
    object Preparing : SearchState()
    data class Searching(
        val searchQuery: SearchQuery,
        val shopPagingDataFlow: Flow<PagingData<Shop>> = flow { PagingData.empty<Shop>() },
    ) : SearchState() {
        companion object {
            fun from(
                searchQuery: SearchQuery,
                shopUseCase: ShopUseCase,
                coroutineScope: CoroutineScope
            ) = Searching(
                searchQuery = searchQuery,
                shopPagingDataFlow = Pager(PagingConfig(pageSize = ShopPagingSource.PAGE_SIZE)) {
                    ShopPagingSource(shopUseCase, searchQuery)
                }.flow.cachedIn(coroutineScope)
            )
        }
    }
}
