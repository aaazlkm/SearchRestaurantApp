package com.example.presentation.shop.list.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.core.result.Result
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.Shop
import com.example.domain.shop.usecase.ShopUseCase

class ShopSource constructor(
    private val shopUseCase: ShopUseCase,
    private val searchQuery: SearchQuery,
) : PagingSource<Int, Shop>() {

    companion object {
        const val PAGE_SIZE = 30
    }

    override fun getRefreshKey(state: PagingState<Int, Shop>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shop> {
        val pageToBeLoaded = params.key ?: 1
        val result = shopUseCase.searchNearShops(
            searchQuery.copy(
                start = pageToBeLoaded,
                count = PAGE_SIZE
            )
        )
        return when (result) {
            is Result.Success -> LoadResult.Page(
                data = result.value.shops,
                prevKey = null,
                nextKey = if (result.value.hasNextPage) pageToBeLoaded + 1 else null
            )
            is Result.Error -> {
                result.e.printStackTrace()
                LoadResult.Error(result.e)
            }
        }
    }
}
