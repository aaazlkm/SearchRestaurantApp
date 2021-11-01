package com.example.domain.shop.repository

import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId

interface ShopRepository {
    suspend fun searchNearShops(
        searchQuery: SearchQuery
    ): SearchResult

    suspend fun searchShop(
        shopId: ShopId,
    ): Shop
}
