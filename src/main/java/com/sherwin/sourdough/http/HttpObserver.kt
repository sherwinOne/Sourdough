package com.sherwin.sourdough.http

import com.sherwin.sourdough.context.BaseApplication
import com.sherwin.sourdough.rx.SimpleObserver
import io.reactivex.Observer

object HttpObserver {
    @JvmStatic fun <T> with(nextAction: (T) -> Unit, errorAction: (Throwable?) -> Unit): Observer<T> {
        return SimpleObserver.with(nextAction) { e ->
            errorAction(e)
            if (e != null) BaseApplication.instance.handleHttpError(e)
        }
    }

    @JvmStatic fun <T> with(nextAction: (T) -> Unit) = with(nextAction) {}
}
