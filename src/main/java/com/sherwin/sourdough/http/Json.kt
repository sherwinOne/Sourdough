package com.sherwin.sourdough.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Json {
    val gson by lazy { Gson() }

    fun <T> parse(json: String, cls: Class<T>): T {
        return gson.fromJson(json, cls)
    }

    fun <T> parse(json: String, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun <T> stringify(obj: T): String {
        return gson.toJson(obj)
    }

    fun <T> parse(json: String) : T{
        return parse(json,object : TypeToken<T>() {}.type)
    }

    fun <T> parseArrayList(json: String, clazz: Class<T>): ArrayList<T> {
        val type = com.google.gson.internal.
                `$Gson$Types`.newParameterizedTypeWithOwner(null, ArrayList::class.java, clazz) as Type
        return Gson().fromJson(json, type) as ArrayList<T>
    }

}
