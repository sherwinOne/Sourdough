package com.sherwin.sourdough.http

import java.lang.reflect.Type
import kotlin.reflect.KClass

fun <T : Any> Type.fromJson(json: String): T = Json.parse(json, this)
fun <T : Any> KClass<T>.fromJson(json: String): T = Json.parse(json, java)
fun <T : Any> T.toJson(): String = Json.stringify(this)