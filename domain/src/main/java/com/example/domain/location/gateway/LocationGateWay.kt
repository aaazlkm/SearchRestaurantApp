package com.example.domain.location.gateway

import com.example.domain.location.model.GetLocationResult

interface LocationGateWay {
    suspend fun lastLocation(): GetLocationResult
}
