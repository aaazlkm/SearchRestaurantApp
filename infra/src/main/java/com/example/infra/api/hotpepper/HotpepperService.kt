package com.example.infra.api.hotpepper

import com.example.infra.api.hotpepper.api.FetchNearShopsAPI
import com.example.infra.api.hotpepper.api.FetchShopAPI
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface HotpepperService {

    @GET
    suspend fun fetchNearShops(
        @Url url: String,
        @QueryMap parameters: Map<String, String>
    ): FetchNearShopsAPI.Response

    @GET
    suspend fun fetchShop(
        @Url url: String,
        @QueryMap parameters: Map<String, String>
    ): FetchShopAPI.Response
}
