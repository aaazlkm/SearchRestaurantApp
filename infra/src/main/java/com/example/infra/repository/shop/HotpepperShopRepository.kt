package com.example.infra.repository.shop

import com.example.domain.core.error.AppError
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.repository.ShopRepository
import com.example.infra.api.hotpepper.HotpepperService
import com.example.infra.api.hotpepper.api.SearchNearShopsAPI
import com.example.infra.api.hotpepper.api.SearchShopAPI
import com.example.infra.api.hotpepper.callHotPepperAPiSafely
import com.example.infra.api.hotpepper.mapper.SearchRangeMapper
import com.example.infra.api.hotpepper.mapper.SearchResultsMapper
import javax.inject.Inject

class HotpepperShopRepository @Inject constructor(
    private val hotpepperService: HotpepperService,
) : ShopRepository {
    override suspend fun searchNearShops(
        searchQuery: SearchQuery
    ): SearchResult {
        val request = SearchNearShopsAPI.Request(
            lat = searchQuery.location.latitude,
            lng = searchQuery.location.longitude,
            range = SearchRangeMapper.toData(searchQuery.searchRange),
            start = searchQuery.start,
            count = searchQuery.count
        )
        return callHotPepperAPiSafely {
            hotpepperService.searchNearShops(request.path.value, request.queryParameter)
        }.let { response ->
            SearchResultsMapper.fromData(response.results)
        }
    }

    override suspend fun searchShop(
        shopId: ShopId
    ): Shop {
        val request = SearchShopAPI.Request(
            shopId = shopId.value
        )
        return callHotPepperAPiSafely {
            hotpepperService.searchShop(request.path.value, request.queryParameter)
        }.let { response ->
            SearchResultsMapper.fromData(response.results).shops.singleOrNull() { it.id == shopId }
                ?: throw AppError.Api.NotFoundedException("該当するShopは存在しません")
        }
    }
}
