package com.sherwin.sourdough.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

open class BaseMvpPresenter<V : BaseMvpView?> : Presenter<V> {
    /**
     * 返回目标view
     * @return
     */
    var mvpView: V? = null
        private set

    override fun attachView(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    /**
     * 绑定presenter生命周期
     */
    fun bindToLifecycle(view : V,lifecycle: Lifecycle){
        lifecycle.addObserver(object : LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate(){
                attachView(view)
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(){
                detachView()
            }
        })
    }

    /**
     * 判断 view是否为空
     * @return
     */
    val isAttachView: Boolean
        get() = mvpView != null

    /**
     * 检查view和presenter是否连接
     */
    fun checkViewAttach() {
        if (!isAttachView) {
            throw MvpViewNotAttachedException()
        }
    }

    /**
     * 自定义异常
     */
    class MvpViewNotAttachedException :
        RuntimeException("请求数据前请先调用 attachView(MvpView) 方法与View建立连接")
}