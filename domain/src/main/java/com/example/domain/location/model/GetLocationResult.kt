package com.example.domain.location.model

sealed class GetLocationResult {
    data class Success(val location: Location) : GetLocationResult()
    object NoPermission : GetLocationResult()
}
