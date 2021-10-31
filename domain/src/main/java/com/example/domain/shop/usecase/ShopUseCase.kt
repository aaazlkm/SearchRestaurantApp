package com.example.domain.shop.usecase

import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.repository.ShopRepository
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
) {
    suspend fun fetchNearShops(
        searchQuery: SearchQuery,
    ): SearchResult = shopRepository.fetchNearShops(
        searchQuery = searchQuery,
    )

    suspend fun fetchShop(
        shopId: ShopId,
    ): Shop {
        return shopRepository.fetchShop(
            shopId = shopId
        )
    }
}
