package com.example.domain.location.gateway

import com.example.domain.location.model.Location

interface LocationGateWay {
    suspend fun lastLocation(): Location
}
