package com.example.infra.repository.shop

import com.example.domain.core.error.AppError
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.repository.ShopRepository
import com.example.infra.api.hotpepper.HotpepperService
import com.example.infra.api.hotpepper.api.FetchNearShopsAPI
import com.example.infra.api.hotpepper.api.FetchShopAPI
import com.example.infra.api.hotpepper.core.callHotPepperAPiSafely
import com.example.infra.api.hotpepper.mapper.SearchRangeMapper
import com.example.infra.api.hotpepper.mapper.SearchResultsMapper
import javax.inject.Inject

class HotpepperShopRepository @Inject constructor(
    private val hotpepperService: HotpepperService,
) : ShopRepository {
    override suspend fun fetchNearShops(
        searchQuery: SearchQuery
    ): SearchResult {
        val request = FetchNearShopsAPI.Request(
            lat = searchQuery.location.lat,
            lng = searchQuery.location.lng,
            range = SearchRangeMapper.toData(searchQuery.searchRange),
            start = searchQuery.start,
            count = searchQuery.count
        )
        return callHotPepperAPiSafely {
            hotpepperService.fetchNearShops(request.path.value, request.queryParameter)
        }.let { response ->
            SearchResultsMapper.fromData(response.results)
        }
    }

    override suspend fun fetchShop(
        shopId: ShopId
    ): Shop {
        val request = FetchShopAPI.Request(
            shopId = shopId.value
        )
        return callHotPepperAPiSafely {
            hotpepperService.fetchShop(request.path.value, request.queryParameter)
        }.let { response ->
            SearchResultsMapper.fromData(response.results).shops.singleOrNull() { it.id == shopId }
                ?: throw AppError.ApiException.NotFoundedException("該当するShopは存在しません")
        }
    }
}
