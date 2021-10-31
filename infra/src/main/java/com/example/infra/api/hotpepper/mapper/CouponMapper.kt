package com.example.infra.api.hotpepper.mapper

import com.example.domain.core.error.AppError
import com.example.domain.shop.model.Coupon
import com.example.infra.api.hotpepper.model.ShopData

object CouponMapper {
    fun fromData(data: ShopData): Coupon = when (
        data.ktaiCoupon
    ) {
        0 -> Coupon.Url(data.couponUrls.sp)
        1 -> Coupon.None
        else -> throw AppError.ApiException.ParseDataException("ktaiCouponは0,1しか取りません")
    }
}
