package com.example.domain.shop.model

data class Genre(
    val code: GenreCode,
    val name: String,
    val catch: String
)

@JvmInline
value class GenreCode(val value: String)
