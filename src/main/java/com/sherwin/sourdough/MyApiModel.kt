package com.sherwin.sourdough

import com.sherwin.sourdough.ErrorCode.SUCCESS
import com.google.gson.annotations.SerializedName
import com.sherwin.sourdough.http.Bean

/**
 * Created by Administrator on 2017/11/8.
 */
data class MyApiModel<out D>(
        @SerializedName("code") val code: Int,
        @SerializedName("message") val msg: String,
        @SerializedName("data") private val _data: D?
) : Bean() {
    val isSuccess: Boolean get() = code == SUCCESS

    @Suppress("UNCHECKED_CAST")
    val data: D get() = if (isSuccess) _data ?: "" as D else throw ErrorCode.CodeException(
        code,
        msg,
        _data.toString()
    )


}