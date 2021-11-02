package com.example.infra.gateway

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.domain.core.error.AppError
import com.example.domain.location.gateway.LocationGateWay
import com.example.domain.location.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class AndroidGpsLocationGateWay @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationGateWay {
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @ExperimentalCoroutinesApi
    override suspend fun lastLocation(): Location {
        val hasAccessFineLocation = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocation = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!(hasAccessFineLocation || hasAccessCoarseLocation)) {
            throw AppError.Permission.NoLocationPermissionException("位置情報のパーミッションがありません")
        }
        return suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { it ->
                val location = Location(latitude = it.latitude, longitude = it.longitude)
                continuation.resume(location) { e ->
                    continuation.resumeWithException(e)
                }
            }.addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
        }
    }
}
