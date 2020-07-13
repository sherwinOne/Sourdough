package com.sherwin.sourdough.candy

fun <T : Any> kotlin.reflect.KClass<T>.typeWith(vararg args: java.lang.reflect.Type) = object : java.lang.reflect.ParameterizedType {
    override fun getRawType() = java

    override fun getOwnerType() = null

    override fun getActualTypeArguments() = args
}

val <T : Any> kotlin.reflect.KClass<T>.typeArgs: Array<java.lang.reflect.Type> get() = (java.genericSuperclass as java.lang.reflect.ParameterizedType).actualTypeArguments

fun <T: Any> T.invokeMethod(method: java.lang.reflect.Method, args: Array<out Any>?): Any? {
    return if (args == null) method.invoke(this) else method.invoke(this, *args)
}