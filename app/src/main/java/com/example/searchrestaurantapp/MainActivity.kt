package com.example.searchrestaurantapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import com.example.presentation.AppContent
import com.example.presentation.ProvideViewModels
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @ExperimentalPermissionsApi
    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideViewModels {
                AppContent(
                    modifier = Modifier
                        .navigationBarsPadding(bottom = false)
                )
            }
        }
    }
}
