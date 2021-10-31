package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.Location
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.infra.api.hotpepper.model.ShopData

object ShopMapper {
    fun fromData(data: ShopData): Shop = Shop(
        id = ShopId(data.id),
        name = data.name,
        nameKana = data.nameKana,
        logoImage = data.logoImage,
        address = data.address,
        stationName = data.stationName,
        location = Location(data.lat, data.lng),
        genre = GenreMapper.fromData(data.genre),
        budget = BudgetMapper.fromData(data.budget),
        budgetMemo = data.budgetMemo,
        capacity = data.capacity,
        catch = data.catch,
        mobileAccess = data.mobileAccess,
        urls = UrlsMapper.fromData(data.urls),
        photo = PhotoMapper.fromData(data.photo),
        open = data.open,
        close = data.close,
        coupon = CouponMapper.fromData(data)
    )
}
