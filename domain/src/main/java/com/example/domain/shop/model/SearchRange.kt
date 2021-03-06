package com.example.domain.shop.model

enum class SearchRange {
    L_300,
    L_500,
    L_1000,
    L_2000,
    L_3000;

    companion object {
        val sortedByRange: List<SearchRange> = listOf(
            L_300,
            L_500,
            L_1000,
            L_2000,
            L_3000,
        )
    }
}
