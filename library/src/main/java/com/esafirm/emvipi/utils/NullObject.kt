package com.esafirm.emvipi.utils

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object NullObject {

    fun <T> create(clazz: Class<T>): T {
        val localClassLoader = clazz.classLoader
        val localNullInvocationHandler = NullInvocationHandler()
        return clazz.cast(Proxy.newProxyInstance(localClassLoader, arrayOf<Class<*>>(clazz), localNullInvocationHandler))
    }

    private class NullInvocationHandler : InvocationHandler {
        override fun invoke(`object`: Any, method: Method, args: Array<Any>): Any? {
            return null
        }
    }
}
