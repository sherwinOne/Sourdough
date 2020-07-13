package com.sherwin.sourdough.http

import com.google.gson.annotations.SerializedName

data class ApiModel<out D>(
        @SerializedName("code") val code: Int,
        @SerializedName("msg") val msg: String,
        @SerializedName("page") val page: Int,
        @SerializedName("size") val size: Int,
        @SerializedName("data") private val _data: D?
) : Bean() {
    val isSuccess: Boolean get() = code == SUCCESS

    @Suppress("UNCHECKED_CAST")
    val data: D get() = if (isSuccess) _data ?: "" as D else throw CodeException(code, msg)

    companion object {
        const val SUCCESS = 0
        const val UUID_EXPIRED = 1042
        const val INVALID_TOKEN = 1043
        const val LOGIN_IN_ON_OTHER_DEVICE = 3023
        const val ORDER_NOT_REPEAT_COMMIT = 1001
    }

    open class CodeException(val code: Int, val msg: String) : RuntimeException() {
        override fun toString() = "CodeException: { code = $code, msg = $msg }"
    }
    open class NullDataException : RuntimeException()
}
