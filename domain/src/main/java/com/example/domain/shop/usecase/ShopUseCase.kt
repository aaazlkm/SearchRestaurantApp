package com.example.domain.shop.usecase

import com.example.domain.core.result.Result
import com.example.domain.core.result.wrapByResult
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.repository.CacheShopRepository
import com.example.domain.shop.repository.ShopRepository
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
    private val cacheShopRepository: CacheShopRepository,
) {
    suspend fun searchNearShops(
        searchQuery: SearchQuery,
    ): Result<SearchResult> = wrapByResult {
        val searchResult = shopRepository.searchNearShops(
            searchQuery = searchQuery,
        )
        cacheShopRepository.saveShopsInCache(searchResult.shops)
        return@wrapByResult searchResult
    }

    suspend fun searchShop(
        shopId: ShopId,
    ): Result<Shop> = wrapByResult {
        cacheShopRepository.fetchShop(shopId) ?: shopRepository.searchShop(shopId = shopId)
    }
}
