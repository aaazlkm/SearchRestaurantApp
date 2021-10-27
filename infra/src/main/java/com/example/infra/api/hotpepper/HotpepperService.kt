package com.example.infra.api.hotpepper

import com.example.infra.api.hotpepper.api.GetGourmetAPI
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface HotpepperService {

    @GET
    suspend fun fetchShops(
        @Url url: String,
        @QueryMap parameters: Map<String, String>
    ): GetGourmetAPI.Response
}
