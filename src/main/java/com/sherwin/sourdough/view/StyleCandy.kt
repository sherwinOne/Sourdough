package com.sherwin.sourdough.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes

inline fun AttributeSet.resolve (context: Context, @StyleableRes attrs:IntArray, func: TypedArray.()->Unit) {
    context.obtainStyledAttributes(this, attrs).apply { func() }.recycle()
}