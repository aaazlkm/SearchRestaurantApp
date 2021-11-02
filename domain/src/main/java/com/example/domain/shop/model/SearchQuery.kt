package com.example.domain.shop.model

import com.example.domain.location.model.Location

data class SearchQuery(
    val location: Location,
    val searchRange: SearchRange = SearchRange.L_1000,
    val start: Int = 1,
    val count: Int = 30,
) {
    data class Builder(
        val searchRange: SearchRange = SearchRange.L_1000,
    ) {

        fun setSearchRange(searchRange: SearchRange) = copy(searchRange = searchRange)

        fun build(location: Location): SearchQuery = SearchQuery(
            location = location,
            searchRange = searchRange,
        )
    }
}
