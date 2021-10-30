package com.example.searchrestaurantapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.Modifier
import com.example.domain.shop.usecase.ShopUseCase
import com.example.presentation.AppContent
import com.example.presentation.ProvideViewModels
import com.google.accompanist.insets.navigationBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var shopUseCase: ShopUseCase

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
