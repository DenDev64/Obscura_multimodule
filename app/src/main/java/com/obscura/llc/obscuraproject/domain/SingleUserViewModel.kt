package com.obscura.llc.obscuraproject.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.obscura.llc.moduleproject.usecases.Interactor
import com.obscura.llc.obscuraproject.presentation.widget.SingleLiveEvent
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

class SingleUserViewModel(application: Application, private val interactor: Interactor) : BaseAndroidViewModel(application) {

    private val liveDataItem = SingleLiveEvent<UserEntity>()

    @SuppressLint("CheckResult")
    fun getItem(id: Int) {
        interactor.getUser(id).subscribe { list -> liveDataItem.value = list }
    }

    fun getLiveDataItem(): LiveData<UserEntity> {
        return liveDataItem
    }
}