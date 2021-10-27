package com.example.infra.api.hotpepper.model

interface HotpepperResult {
    /**
     * エラーの時ここにレスポンスが入る
     */
    val errors: List<HotpepperErrorInfoData>?
}
