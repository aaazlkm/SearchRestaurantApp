package com.example.infra.api.hotpepper.mapper

import com.example.domain.shop.model.Mobile
import com.example.domain.shop.model.Photo
import com.example.infra.api.hotpepper.model.MobileData
import com.example.infra.api.hotpepper.model.PhotoData

object PhotoMapper {
    fun fromData(data: PhotoData): Photo = Photo(
        mobile = MobileMapper.fromData(data.mobile)
    )
}

object MobileMapper {
    fun fromData(data: MobileData): Mobile = Mobile(
        l = data.l,
        s = data.s,
    )
}
