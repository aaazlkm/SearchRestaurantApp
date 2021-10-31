package com.example.infra.api.hotpepper.model

data class ShopData(
    val id: String,
    val name: String,
    val nameKana: String,
    val logoImage: String,
    val address: String,
    val stationName: String,
    val ktaiCoupon: Int,
    val lat: Double,
    val lng: Double,
    val genre: GenreData,
    val budget: BudgetData,
    val budgetMemo: String,
    val catch: String,
    val capacity: String,
    val mobileAccess: String,
    val urls: UrlsData,
    val photo: PhotoData,
    val open: String,
    val close: String,
    val couponUrls: CouponUrlsData,
)
