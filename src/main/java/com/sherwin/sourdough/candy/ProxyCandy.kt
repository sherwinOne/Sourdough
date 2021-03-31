package com.sherwin.sourdough.candy

@Suppress("UNCHECKED_CAST")
fun <T : Any> T.newProxy(func: T.(java.lang.reflect.Method, Array<out Any>?) -> Any): T {
    val handler = ProxyHandler(this, func)
    return java.lang.reflect.Proxy.newProxyInstance(handler.javaClass.classLoader, javaClass.interfaces, handler) as T
}

private class ProxyHandler<T>(val t: T, val func: T.(java.lang.reflect.Method, Array<out Any>?) -> Any) : java.lang.reflect.InvocationHandler {
    override fun invoke(proxy: Any, method: java.lang.reflect.Method, args: Array<out Any>?): Any {
        return t.func(method, args)
    }
}