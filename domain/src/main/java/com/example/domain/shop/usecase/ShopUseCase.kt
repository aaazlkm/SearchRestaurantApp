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
    suspend fun searchNearShops(
        searchQuery: SearchQuery,
    ): SearchResult = shopRepository.searchNearShops(
        searchQuery = searchQuery,
    )

    suspend fun searchShop(
        shopId: ShopId,
    ): Shop {
        return shopRepository.searchShop(
            shopId = shopId
        )
    }
}
