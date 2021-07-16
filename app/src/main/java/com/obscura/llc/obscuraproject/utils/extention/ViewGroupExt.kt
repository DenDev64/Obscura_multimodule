package com.obscura.llc.obscuraproject.utils.extention

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflateLayout(layoutRes: Int, attachToRoot: Boolean = true): View=
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)