package com.sherwin.sourdough.http

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

class YaRxJava2CallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *> {
        @Suppress("UNCHECKED_CAST")
        val wrapped = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()).get(returnType, annotations, retrofit) as CallAdapter<Any, Any>
        return object : CallAdapter<Any, Any> {
            override fun responseType() = wrapped.responseType()

            override fun adapt(call: Call<Any>?) =
                    (wrapped.adapt(call) as Observable<*>)
                            .observeOn(AndroidSchedulers.mainThread())
        }
    }
}