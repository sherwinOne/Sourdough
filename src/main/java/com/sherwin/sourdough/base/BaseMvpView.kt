package com.sherwin.sourdough.base

/**
 * mvp view基本类
 */
interface BaseMvpView {
    /**
     * 显示等待框
     * msg: 加载提示文本
     */
    fun showLoading(msg: String = "")

    //隐藏等待框
    fun hideLoading()

    //错误信息
    fun showErr(err: String?)
}