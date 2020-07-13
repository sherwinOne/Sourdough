package com.sherwin.sourdough.http

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import kotlin.reflect.KClass

open class BaseRetrofitProxy(val baseUrl: String) {

    open fun onBuildOkHttpClient(builder: OkHttpClient.Builder) {}

    open var factory : Converter.Factory = YaGsonConverterFactory()

    open fun <T : Any> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    fun <T : Any> create(clazz: KClass<T>): T = create(clazz.java)

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(YaRxJava2CallAdapterFactory())
                .build()
    }

    private val okHttpClient by lazy {
        val loggingLevel = HttpLoggingInterceptor.Level.BODY
//        val loggingLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(loggingLevel))
                .apply { onBuildOkHttpClient(this) }
                .build()
    }
}