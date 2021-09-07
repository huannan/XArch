package com.nan.xarch.bean.exception

class NetworkException private constructor(val code: Int, message: String) : RuntimeException(message) {

    override fun toString(): String {
        return "exception code is $code msg is $message"
    }

    companion object {
        @JvmStatic
        fun of(code: Int, message: String) = NetworkException(code, message)
    }

}