package com.example.infra.api.hotpepper.model

data class ShopData(
    val id: String,
    val name: String,
    val logoImage: String,
    val nameKana: String,
    val address: String,
    val stationName: String,
    val lat: Double,
    val lng: Double,
    val genre: GenreData,
    val budget: BudgetData,
    val budgetMemo: String,
    val capacity: String,
    val urls: UrlsData,
    val photo: PhotoData,
    val open: String,
    val close: String,
)
