package com.obscura.llc.obscuraproject.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.annotation.StringRes

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    fun getContext() = getApplication<com.obscura.llc.obscuraproject.App>()
    fun getString(@StringRes id: Int): String = getContext().getString(id)
}