package com.example.infra.api.hotpepper.model

import com.google.gson.annotations.SerializedName

data class SearchResultsData(
    val resultsAvailable: Int?,
    val resultsReturned: String?,
    val resultsStart: Int?,
    @SerializedName("shop")
    val shops: List<ShopData>?,
    @SerializedName("error")
    override val errors: List<HotpepperErrorInfoData>?,
) : HotpepperResult
