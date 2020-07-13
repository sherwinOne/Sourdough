package com.sherwin.sourdough

import com.sherwin.sourdough.context.BaseApplication

class App : BaseApplication(){
    override fun handleHttpError(e: Throwable) {

    }

    companion object {
        @JvmStatic
        val instance
            get() = BaseApplication.instance as App
    }
}