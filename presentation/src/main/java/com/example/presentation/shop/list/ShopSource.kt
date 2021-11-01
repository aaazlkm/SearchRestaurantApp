package com.example.presentation.shop.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.core.error.AppError
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
        return try {
            val pageToBeLoaded = params.key ?: 1
            val searchResult = shopUseCase.searchNearShops(
                searchQuery.copy(
                    start = pageToBeLoaded,
                    count = PAGE_SIZE
                )
            )
            LoadResult.Page(
                data = searchResult.shops,
                prevKey = null,
                nextKey = if (searchResult.hasNextPage) pageToBeLoaded + 1 else null
            )
        } catch (exception: AppError) {
            return LoadResult.Error(exception)
        }
    }
}
