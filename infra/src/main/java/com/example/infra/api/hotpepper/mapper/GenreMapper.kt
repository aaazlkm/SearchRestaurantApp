package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.Genre
import com.example.domain.shop.model.GenreCode
import com.example.infra.api.hotpepper.model.GenreData

object GenreMapper {
    fun fromData(data: GenreData): Genre = Genre(
        code = GenreCode(data.code),
        name = data.name,
        catch = data.catch
    )
}
