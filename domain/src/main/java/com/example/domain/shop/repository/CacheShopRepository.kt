package com.example.domain.shop.repository

import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId

interface CacheShopRepository {
    fun saveShopsInCache(shops: List<Shop>)

    fun fetchShop(shopId: ShopId): Shop?
}
