package com.example.presentation.shop.list.model

import androidx.annotation.DrawableRes
import com.example.presentation.R

enum class EmptyImageType {
    SUSHI,
    RAMEN,
    STAKE,
    POTATO,
    FISH,
    SALAD,
    CAKE;

    companion object {
        fun generateRandomly(without: EmptyImageType? = null): EmptyImageType =
            values().asSequence().shuffled().filter { it != without }.first()
    }
}

@DrawableRes
fun EmptyImageType.getDrawable(): Int {
    return when (this) {
        EmptyImageType.SUSHI -> R.drawable.image_sushi
        EmptyImageType.RAMEN -> R.drawable.image_ramen
        EmptyImageType.STAKE -> R.drawable.image_stake
        EmptyImageType.POTATO -> R.drawable.image_potato
        EmptyImageType.FISH -> R.drawable.image_fish
        EmptyImageType.SALAD -> R.drawable.image_salad
        EmptyImageType.CAKE -> R.drawable.image_cake
    }
}
