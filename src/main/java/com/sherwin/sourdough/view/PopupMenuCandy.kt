package com.sherwin.sourdough.view

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu

/**
 * 设置popupMenu的瞄点
 */
@SuppressLint("RestrictedApi")
fun PopupMenu.setAnchor(view: View): PopupMenu {
    try {
        val helperField = this.javaClass.getDeclaredField("mPopup")
        helperField.isAccessible = true
        val helper = helperField.get(this) as MenuPopupHelper
        helper.setAnchorView(view)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this
}
