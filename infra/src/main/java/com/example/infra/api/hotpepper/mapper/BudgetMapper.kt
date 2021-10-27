package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.Budget
import com.example.domain.shop.model.BudgetCode
import com.example.infra.api.hotpepper.model.BudgetData

object BudgetMapper {
    fun fromData(data: BudgetData): Budget = Budget(
        code = BudgetCode(data.code),
        name = data.name,
        average = data.average
    )
}
