package com.sherwin.sourdough.rx

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class EmptyObserver<T> : Observer<T> {
    override fun onNext(t: T) {
    }



    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
    }
}
