package com.sherwin.sourdough.view

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.sherwin.sourdough.view.inflateLayout

fun TypedArray.resolveDrawable(view: ImageView, resId: Int, defaultValue: Drawable? = null) {
    val drawable = getDrawable(resId) ?: defaultValue
    if (drawable != null) {
        view.setImageDrawable(drawable)
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

fun TypedArray.resolveBackground(view: View, resId: Int, defaultValue: Drawable? = null) {
    val drawable = getDrawable(resId) ?: defaultValue
    if (drawable != null) {
        view.background = drawable
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

fun TypedArray.resolveText(view: TextView, resId: Int, defaultValue: String = "") {
    val text = getText(resId) ?: defaultValue
    if (!TextUtils.isEmpty(text)) {
        view.text = text
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

fun TypedArray.resolveTextColor(view: TextView,resId: Int,defaultColor : Int){
    val color = getColor(resId,defaultColor)
    if (color != 0){
        view.setTextColor(color)
    }else{
        view.setTextColor(defaultColor)
    }
}

fun TypedArray.resolveHint (view: EditText, resId:Int, defaultValue:String = "") {
    val text = getText(resId) ?: defaultValue
    if (!TextUtils.isEmpty(text)) {
        view.hint = text
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

fun TypedArray.resolveLayout (view: ViewGroup, resId:Int, defaultLayout:Int) {
    view.inflateLayout(getResourceId(resId, defaultLayout))
}