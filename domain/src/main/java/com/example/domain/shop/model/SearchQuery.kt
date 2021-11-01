package com.example.domain.shop.model

import com.example.domain.location.model.Location

data class SearchQuery(
    val location: Location = Location.tokyo,
    val searchRange: SearchRange = SearchRange.L_1000,
    val start: Int = 1,
    val count: Int = 30,
)
