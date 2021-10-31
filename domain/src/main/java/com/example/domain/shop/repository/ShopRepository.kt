package com.example.domain.shop.repository

import com.example.domain.shop.model.Location
import com.example.domain.shop.model.SearchRange
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId

interface ShopRepository {
    suspend fun fetchNearShops(
        location: Location,
        searchRange: SearchRange,
        start: Int,
        count: Int
    ): SearchResult

    suspend fun fetchShop(
        shopId: ShopId,
    ): Shop
}
