package com.example.infra.api.hotpepper

import com.example.core.BuildConfig

object HotpepperAPIConfiguration {
    const val HOTPEPPER_API_KEY = BuildConfig.HOTPEPPER_API_KEY

    const val HOTPEPPER_BASE_URL = "http://webservice.recruit.co.jp/hotpepper/"

    const val format: String = "json"
}
