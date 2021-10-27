package com.example.domain.shop.model

data class Budget(
    val code: BudgetCode,
    val name: String,
    val average: String
)

@JvmInline
value class BudgetCode(val value: String)
