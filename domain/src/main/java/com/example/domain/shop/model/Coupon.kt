package com.example.domain.shop.model

sealed class Coupon {
    object None : Coupon()

    data class Url(
        val sp: String,
    ) : Coupon()
}
