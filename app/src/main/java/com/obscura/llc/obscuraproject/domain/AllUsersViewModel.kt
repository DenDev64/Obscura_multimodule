package com.obscura.llc.obscuraproject.domain

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import com.obscura.llc.moduleproject.usecases.Interactor
import com.obscura.llc.obscuraproject.presentation.widget.SingleLiveEvent
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity

//todo create paging list utils
//todo create mapper
//todo create multicontentview
//todo create rxutils
class AllUsersViewModel(application: Application, private val interactor: Interactor) : BaseAndroidViewModel(application) {
    private val liveDataItems = SingleLiveEvent<List<UserEntity>>()

    @SuppressLint("CheckResult")
    fun getAllItems() {
        interactor.getAll()?.subscribe { list -> liveDataItems.value = list }
    }

    fun getLiveDataItems(): LiveData<List<UserEntity>> {
        return liveDataItems
    }

    override fun onCleared() {
        super.onCleared()
    }
}

