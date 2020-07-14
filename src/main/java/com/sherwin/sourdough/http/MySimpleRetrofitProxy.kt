package com.sherwin.sourdough.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.sherwin.sourdough.context.BaseApplication
import okhttp3.OkHttpClient
import retrofit2.Converter

/**
 * Created by Administrator on 2017/11/8.
 */
open class MySimpleRetrofitProxy(baseUrl: String
//                                 , private val appId: String, private var appSecret: String
) : BaseRetrofitProxy(baseUrl) {
    override var factory: Converter.Factory = MyYaGsonConverterFactory()
//    private val random by lazy { Random(System.currentTimeMillis()) }
//
    override fun onBuildOkHttpClient(builder: OkHttpClient.Builder) {
        super.onBuildOkHttpClient(builder)
//        builder.addInterceptor {
//            val timestamp = System.currentTimeMillis() / 1000L
//            val nonce = random.nextLong().toString()
//            it.proceed(it.request().newBuilder()
//                    .addHeader("Accept", "application/json")
//                    .addHeader("appid", appId)
//                    .addHeader("timestamp", timestamp.toString())
//                    .addHeader("sign", MD5.encrypt(appId + timestamp + nonce + appSecret))
//                    .addHeader("nonce", nonce)
//                    .addHeader("appver", BaseApplication.instance.packageInfo.versionCode.toString())
//                    .build()
//            )
//        }
        builder.cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(BaseApplication.instance.applicationContext)))
    }
}