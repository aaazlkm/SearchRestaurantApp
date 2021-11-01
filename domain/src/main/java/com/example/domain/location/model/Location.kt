package com.example.domain.location.model

data class Location(
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        val tokyo = Location(latitude = 35.6938, longitude = 139.7034)
    }
}
