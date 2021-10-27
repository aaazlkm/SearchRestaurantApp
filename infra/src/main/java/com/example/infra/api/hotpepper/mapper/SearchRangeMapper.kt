package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.SearchRange

object SearchRangeMapper {
    fun toData(searchRange: SearchRange): Int = when (searchRange) {
        SearchRange.L_300 -> 1
        SearchRange.L_500 -> 2
        SearchRange.L_1000 -> 3
        SearchRange.L_2000 -> 4
        SearchRange.L_3000 -> 5
    }
}
