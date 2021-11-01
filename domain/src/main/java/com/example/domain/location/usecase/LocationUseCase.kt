package com.example.domain.location.usecase

import com.example.domain.location.gateway.LocationGateWay
import com.example.domain.location.model.GetLocationResult
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationGateWay: LocationGateWay,
) {
    suspend fun lastLocation(): GetLocationResult = locationGateWay.lastLocation()
}
