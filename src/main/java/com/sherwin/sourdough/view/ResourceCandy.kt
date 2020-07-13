package com.sherwin.sourdough.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat

fun Context.colorOf(resId: Int) = ContextCompat.getColor(this, resId)

fun View.colorOf(resId: Int) = context.colorOf(resId)

fun Context.drawableOf(resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)

fun View.drawableOf(resId: Int) = context.drawableOf(resId)

fun Context.inflateLayout(resId: Int, root: ViewGroup? = null, attachToRoot: Boolean = root != null): View =
        LayoutInflater.from(this).inflate(resId, root, attachToRoot)

fun ViewGroup.inflateLayout(resId: Int, attachToRoot: Boolean = true) =
        context.inflateLayout(resId, this, attachToRoot)




