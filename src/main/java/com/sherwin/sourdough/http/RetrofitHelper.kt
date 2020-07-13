package com.sherwin.sourdough.http


import kotlin.reflect.KClass

object RetrofitHelper {
    val baseUrl = "http://rap2.taobao.org:38080/app/mock/258384/"

    private val simpleRetrofit by lazy { MySimpleRetrofitProxy(baseUrl) }

//    private val userRetrofit by lazy {
//        object : MyUserRetrofitProxy(baseUrl) {
//            override fun login() = MyUser.login()
//        }
//    }


    fun <T : Any> create(clazz: Class<T>): T {
//        val retrofit = when {
//            clazz.isAnnotationPresent(NoUuid::class.java) -> simpleRetrofit
//            else -> userRetrofit
//        }
        val retrofit = simpleRetrofit

        return retrofit.create(clazz)
    }

    fun <T : Any> create(clazz: KClass<T>): T {
        return create(clazz.java)
    }

    inline fun <reified T : Any> create(): T {
        return create(T::class)
    }

//    fun resolveLoginResult(loginResult: LoginResult) = userRetrofit.resolveLoginResult(loginResult)
}