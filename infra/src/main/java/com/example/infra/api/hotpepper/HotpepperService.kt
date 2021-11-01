package com.example.infra.api.hotpepper

import com.example.infra.api.hotpepper.api.SearchNearShopsAPI
import com.example.infra.api.hotpepper.api.SearchShopAPI
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface HotpepperService {

    @GET
    suspend fun searchNearShops(
        @Url url: String,
        @QueryMap parameters: Map<String, String>
    ): SearchNearShopsAPI.Response

    @GET
    suspend fun searchShop(
        @Url url: String,
        @QueryMap parameters: Map<String, String>
    ): SearchShopAPI.Response
}
