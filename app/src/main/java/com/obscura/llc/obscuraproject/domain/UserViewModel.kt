package com.obscura.llc.obscuraproject.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.obscura.llc.obscuraproject.presentation.base.ItemsLoadListener
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel
import com.obscura.llc.moduleproject.data_source.data.ItemClickListener
import com.obscura.llc.moduleproject.data_source.database.converters.ConverterFactory
import com.obscura.llc.moduleproject.data_source.database.entity.UserEntity
import com.obscura.llc.moduleproject.usecases.UserUseCases
import java.util.NoSuchElementException

/**
 *
 */
class UserViewModel(private val userUseCases: UserUseCases) : BasePagingViewModel() {

    init {
        initPagedConfig()
    }

    /**
     *
     */
    fun initLiveData(type: String, listener: ItemsLoadListener<PagedList<BaseCardModel>>, clickListener: ItemClickListener<UserEntity>) {
        itemLoadedListener = listener
        initPagedListLiveData(userUseCases.getCardsFactory(type, ConverterFactory(clickListener)))
    }


    /**
     *
     */
    fun getPagedList(): LiveData<PagedList<BaseCardModel>> = listCards

    override fun fetchData(screenType : String) {
        compositeDisposable.add(userUseCases.fetchData(screenType)
            .subscribe({ setRefreshing(false) }, { throwable ->
                if (throwable is NoSuchElementException) {
                    itemLoadedListener.onItemsLoaded(null)
                } else {
                    throwable.message?.let { itemLoadedListener.onLoadError(it) }
                }
                setRefreshing(false)
            })
        )
    }

    override fun rangeData(screenType: String, itemId: String) {
        setLoading(true)
        compositeDisposable.add(userUseCases.fetchNext(screenType, itemId)
            .subscribe({ setLoading(false) },
                { setLoading(false) }
            )
        )
    }

    override fun clearCachedItems(screenType : String) {
        userUseCases.deleteCachedItems(screenType)
    }
}