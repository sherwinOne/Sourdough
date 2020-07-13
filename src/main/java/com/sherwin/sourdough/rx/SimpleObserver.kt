package com.sherwin.sourdough.rx

import com.sherwin.sourdough.etc.whenDebug
import io.reactivex.Observer

object SimpleObserver {
    @JvmStatic fun <T> with(nextAction: (T) -> Unit, errorAction: (Throwable?) -> Unit): Observer<T> {
        return object : EmptyObserver<T>() {
            override fun onNext(t: T): Unit {
                try {
                    nextAction(t)
                } catch (e: Exception) {
                    onError(e)
                }
            }

            override fun onError(e: Throwable) {
                errorAction(e)
                whenDebug { e.printStackTrace() }
            }
        }
    }

    @JvmStatic fun <T> with(nextAction: (T) -> Unit) = with(nextAction) {}
}
