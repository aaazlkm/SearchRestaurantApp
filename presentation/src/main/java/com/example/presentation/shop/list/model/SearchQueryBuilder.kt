package com.example.presentation.shop.list.model

import com.example.domain.location.model.Location
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchRange

data class SearchQueryBuilder(
    val location: Location = Location.tokyo,
    val searchRange: SearchRange = SearchRange.L_1000,
) {
    companion object {
        fun from(now: SearchQuery): SearchQueryBuilder = SearchQueryBuilder(
            location = now.location,
            searchRange = now.searchRange,
        )
    }

    fun setLocation(location: Location) = copy(location = location)
    fun setSearchRange(searchRange: SearchRange) = copy(searchRange = searchRange)

    fun build(): SearchQuery = SearchQuery(
        location = location,
        searchRange = searchRange,
    )
}
