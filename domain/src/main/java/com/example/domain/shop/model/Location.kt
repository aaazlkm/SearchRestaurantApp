package com.example.domain.shop.model

data class Location(
    val lat: Double,
    val lng: Double
) {
    companion object {
        val tokyo = Location(lat = 35.6938, lng = 139.7034)
    }
}
