package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.Urls
import com.example.infra.api.hotpepper.model.UrlsData

object UrlsMapper {
    fun fromData(data: UrlsData): Urls = Urls(
        pc = data.pc,
    )
}
