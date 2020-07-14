package com.sherwin.sourdough.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxSafeObserver<T> : Observer<T>{
    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onComplete() {
    }

    override fun onNext(t: T) {
    }

}