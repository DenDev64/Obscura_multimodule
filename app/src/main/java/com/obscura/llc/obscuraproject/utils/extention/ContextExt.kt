package com.obscura.llc.obscuraproject.utils.extention

import android.content.Context

fun Context.dpToPx(dp: Int) = dp * resources.displayMetrics.density