package com.sherwin.sourdough.context

import android.app.Activity
import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.sherwin.sourdough.candy.lazySingleton
import com.facebook.stetho.Stetho
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

abstract class BaseApplication : MultiDexApplication() {
    private var initialized = false
    private var topActivityRef: WeakReference<Activity>? = null

    val topActivity get() = topActivityRef?.get()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    abstract fun handleHttpError(e: Throwable)

    fun initialize() {
        if (!initialized) {
            initialized = true
            manageActivityLifecycle()
            Stetho.initializeWithDefaults(this)
            onInitialized()
        }
    }

    val packageInfo: PackageInfo by lazy { packageManager.getPackageInfo(packageName, 0) }

    protected open fun onInitialized() {}

    private fun manageActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityResumed(activity: Activity) {
                topActivityRef = WeakReference(activity)
            }
        })
    }

    companion object {
        @JvmStatic var instance: BaseApplication by Delegates.lazySingleton()
    }
}