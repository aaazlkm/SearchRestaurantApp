package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.SearchResult
import com.example.infra.api.hotpepper.model.SearchResultsData

object SearchResultsMapper {
    fun fromData(data: SearchResultsData): SearchResult {
        return SearchResult(
            resultsAvailable = data.resultsAvailable!!,
            resultsReturned = data.resultsReturned!!.toInt(),
            resultsStart = data.resultsStart!!,
            shops = data.shops!!.map { ShopMapper.fromData(it) }
        )
    }
}
