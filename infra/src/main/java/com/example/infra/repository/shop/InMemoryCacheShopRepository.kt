package com.example.infra.repository.shop

import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.repository.CacheShopRepository

class InMemoryCacheShopRepository : CacheShopRepository {
    private val idToShop = mutableMapOf<ShopId, Shop>()

    override fun saveShopsInCache(shops: List<Shop>) {
        idToShop.putAll(shops.map { it.id to it }.toMap())
    }

    override fun fetchShop(shopId: ShopId): Shop? = idToShop[shopId]
}
