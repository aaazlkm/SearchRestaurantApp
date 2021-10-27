package com.example.domain.shop.usecase

import com.example.domain.core.result.LoadResult
import com.example.domain.core.result.flowWithLoading
import com.example.domain.shop.model.Location
import com.example.domain.shop.model.SearchRange
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
) {
    suspend fun fetchNearShops(
        location: Location,
        searchRange: SearchRange,
        start: Int,
        count: Int
    ): Flow<LoadResult<SearchResult>> = flowWithLoading {
        shopRepository.fetchNearShops(
            location = location,
            searchRange = searchRange,
            start = start,
            count = count
        )
    }
}
