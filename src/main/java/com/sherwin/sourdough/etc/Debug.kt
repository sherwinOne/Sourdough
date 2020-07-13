package com.sherwin.sourdough.etc

import io.reactivex.android.BuildConfig


fun whenDebug(func: () -> Unit) {
    if (BuildConfig.DEBUG) func()
}