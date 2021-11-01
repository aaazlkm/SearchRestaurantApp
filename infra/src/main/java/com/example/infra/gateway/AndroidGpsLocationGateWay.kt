package com.example.infra.gateway

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.domain.location.gateway.LocationGateWay
import com.example.domain.location.model.GetLocationResult
import com.example.domain.location.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class AndroidGpsLocationGateWay @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationGateWay {
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    override suspend fun lastLocation(): GetLocationResult {
        val hasAccessFineLocation = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocation = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!(hasAccessFineLocation || hasAccessCoarseLocation)) {
            return GetLocationResult.NoPermission
        }
        return suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                val location = Location(latitude = it.latitude, longitude = it.longitude)
                val result = GetLocationResult.Success(location)
                continuation.resume(result) {
                    continuation.resumeWithException(it)
                }
            }.addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
        }
    }
}
