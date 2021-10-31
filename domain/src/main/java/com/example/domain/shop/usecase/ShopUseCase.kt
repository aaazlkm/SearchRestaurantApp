package com.example.domain.shop.usecase

import com.example.domain.shop.model.Location
import com.example.domain.shop.model.SearchRange
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.repository.ShopRepository
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
) {
    suspend fun fetchNearShops(
        location: Location,
        searchRange: SearchRange,
        start: Int,
        count: Int
    ): SearchResult = shopRepository.fetchNearShops(
        location = location,
        searchRange = searchRange,
        start = start,
        count = count
    )

    suspend fun fetchShop(
        shopId: ShopId,
    ): Shop {
        return shopRepository.fetchShop(
            shopId = shopId
        )
    }
}
