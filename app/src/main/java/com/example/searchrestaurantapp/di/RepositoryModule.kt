package com.example.searchrestaurantapp.di

import android.content.Context
import com.example.domain.location.gateway.LocationGateWay
import com.example.domain.shop.repository.ShopRepository
import com.example.infra.api.hotpepper.HotpepperService
import com.example.infra.gateway.AndroidGpsLocationGateWay
import com.example.infra.repository.shop.HotpepperShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideSearchRepository(hotpepperService: HotpepperService): ShopRepository =
        HotpepperShopRepository(hotpepperService)

    @Singleton
    @Provides
    fun provideLocationGateWay(
        @ApplicationContext context: Context,
    ): LocationGateWay = AndroidGpsLocationGateWay(context)
}
