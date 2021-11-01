package com.example.domain.location.usecase

import com.example.domain.core.result.Result
import com.example.domain.core.result.wrapByResult
import com.example.domain.location.gateway.LocationGateWay
import com.example.domain.location.model.Location
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationGateWay: LocationGateWay,
) {
    suspend fun lastLocation(): Result<Location> = wrapByResult {
        locationGateWay.lastLocation()
    }
}
