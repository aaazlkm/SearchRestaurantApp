package com.example.presentation.shop.single

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.domain.shop.model.ShopId

@Composable
fun ShopSingleScreen(
    shopId: ShopId,
) {
    Text("ShopSingle$shopId")
}
