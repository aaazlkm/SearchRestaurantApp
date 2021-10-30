package com.example.domain.shop.model

data class SearchResult(
    val resultsAvailable: Int,
    val resultsReturned: Int,
    val resultsStart: Int,
    val shops: List<Shop>
) {

    val hasNextPage: Boolean
        get() = resultsAvailable > resultsStart * resultsReturned
}
