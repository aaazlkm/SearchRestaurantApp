package com.example.infra.api.hotpepper.mapper

import com.example.domain.core.error.HotpepperErrorInfo

object HotpepperErrorInfoMapper {
    fun fromData(data: com.example.infra.api.hotpepper.model.HotpepperErrorInfoData): HotpepperErrorInfo {
        return when (data.code) {
            1000 -> HotpepperErrorInfo.SERVER_ERROR
            2000 -> HotpepperErrorInfo.API_KEY_OR_IP_ADDRESS_AUTH_ERROR
            3000 -> HotpepperErrorInfo.ILLEGAL_PARAMETER
            else -> HotpepperErrorInfo.UNKNOWN
        }
    }
}
