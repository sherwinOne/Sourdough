package com.sherwin.sourdough.http

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type

object JsonObservable {


    inline fun <reified T> parse(jsonStr: String): Observable<T> {
        return Observable.create<T> {
            it.onNext(Json.parse(jsonStr))
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }


    fun stringify(obj: Any): Observable<String> {
        return Observable.create<String> {
            it.onNext(Json.stringify(obj))
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    fun <T> parseArrayList(json: String, clazz: Class<T>): Observable<ArrayList<T>> {
        val type = com.google.gson.internal.
                `$Gson$Types`.newParameterizedTypeWithOwner(null, ArrayList::class.java, clazz) as Type
        return Observable.create<ArrayList<T>> {
            it.onNext(Gson().fromJson(json, type) as ArrayList<T>)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }
}