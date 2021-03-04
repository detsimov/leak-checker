package com.detsimov.leakchecker.domain.exceptions

open class TokenException(message: String) : Exception("An error occurred with the token [$message]")
