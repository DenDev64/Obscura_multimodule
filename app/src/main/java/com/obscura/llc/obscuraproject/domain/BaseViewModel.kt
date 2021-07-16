package com.obscura.llc.obscuraproject.domain

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.obscura.llc.obscuraproject.presentation.base.LoadingState
import com.obscura.llc.obscuraproject.presentation.base.UiError
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable=CompositeDisposable()
    val errorData = MediatorLiveData<UiError>()
    val macroLoadingState = MediatorLiveData<LoadingState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}