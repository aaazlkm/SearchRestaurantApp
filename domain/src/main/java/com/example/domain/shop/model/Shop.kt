package com.example.domain.shop.model

import com.example.domain.location.model.Location

data class Shop(
    val id: ShopId,
    val name: String,
    val nameKana: String,
    val logoImage: String,
    val address: String,
    val stationName: String,
    val location: Location,
    val genre: Genre,
    val budget: Budget,
    val budgetMemo: String,
    val capacity: String,
    val catch: String,
    val mobileAccess: String,
    val urls: Urls,
    val photo: Photo,
    val open: String,
    val close: String,
    val coupon: Coupon,
)

@JvmInline
value class ShopId(val value: String)
