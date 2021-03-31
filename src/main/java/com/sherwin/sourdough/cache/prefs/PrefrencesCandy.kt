package com.sherwin.sourdough.cache.prefs

import com.sherwin.sourdough.http.fromJson
import com.sherwin.sourdough.http.toJson
import java.lang.reflect.Type
import kotlin.reflect.KClass

operator fun <T : Any> Preferences.get(key: String, type: Type): T? {
    val value = get(key)
    return if (value.isEmpty()) null else type.fromJson<T>(value)
}

operator fun <T : Any> Preferences.get(key: String, clazz: KClass<T>): T? = get(key, clazz.java)
operator fun <T : Any> Preferences.set(key: String, t: T) = set(key, t.toJson())

private const val K_DEF = "v"
private val cacheTable = hashMapOf<String, Any>()

fun <T> T.asErrorResult(func: () -> T) = try {
    func()
} catch (e: Exception) {
    this
}