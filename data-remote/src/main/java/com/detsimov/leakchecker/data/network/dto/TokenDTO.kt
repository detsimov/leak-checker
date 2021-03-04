package com.detsimov.leakchecker.data.network.dto

import kotlinx.serialization.Serializable

internal class TokenDTO {


    class Response {
        @Serializable
        data class Create(val token: String)
    }

    class Request {
        @Serializable
        data class Create(val androidId: String)
    }
}

