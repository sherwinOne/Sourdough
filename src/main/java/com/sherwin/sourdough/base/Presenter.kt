package com.sherwin.sourdough.base

internal interface Presenter<V> {
    fun attachView(mvpView: V)
    fun detachView()
}