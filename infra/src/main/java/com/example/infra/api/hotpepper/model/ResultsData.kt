package com.example.infra.api.hotpepper.model

import com.google.gson.annotations.SerializedName

data class ResultsData(
    val resultsAvailable: Int,
    val resultsReturned: String,
    @SerializedName("shop")
    val shops: List<ShopData>
)
