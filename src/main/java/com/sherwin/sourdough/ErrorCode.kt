package com.sherwin.sourdough

object ErrorCode{
    const val SUCCESS = 200
    const val UUID_EXPIRED = -1000
    const val INVALID_TOKEN = 105  //token已过期或错误
    const val ALREADY_REGISTER_ERROR = 101  //用户已注册错误
    const val AUTH_SIM_ERROR = 102  //短信验证码错误
    const val PWD_ERROR = 103//pwd验证失败
    const val ACCOUNT_PWD_ERROR = 104//账号或密码错误
    const val ILLEGAL_PARAMETER_ERROR = 106//非法的参数请求错误
    const val SEND_MSG_FAILE_ERROR = 107//短信验证码发送失败
    const val GATEWAY_IDENTIFY_FAILE_ERROR = 109//请先获取网关权限
    const val GATEWAY_PERMISSION__ERROR = 122//没有网关权限
    const val VOICE_CMD_ERROR = 130//语音理解异常

    open class CodeException(val code1: Int, val msg: String,val data : String) : RuntimeException() {
        override fun toString() = "CodeException: { code = $code1, msg = $msg }"
        override val message: String?
            get() = msg
    }

}