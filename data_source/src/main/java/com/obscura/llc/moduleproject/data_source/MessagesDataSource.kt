package com.obscura.llc.moduleproject.data_source


import androidx.paging.DataSource
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel
import com.obscura.llc.moduleproject.data_source.database.AppDatabase
import com.obscura.llc.moduleproject.data_source.database.converters.ConverterFactory
import com.obscura.llc.moduleproject.data_source.database.entity.MessagesEntity

//todo create abstract parent for DataSource

/**
 *
 */
interface MessagesDataSource {

    /**
     *
     */
    fun getCardsModelsFactory(screenType: String, converterFactory: ConverterFactory)
            : DataSource.Factory<Int, BaseCardModel>

    /**
     *
     */
    fun deleteCachedItems(screenType: String)

    /**
     *
     */
    fun insert(items: List<MessagesEntity>)

    /**
     *
     */
    fun insertAndClearCache(items: List<MessagesEntity>, typeScreen: String?)
}

/**
 *
 */
class MessagesDataSourceImpl(private val database: AppDatabase) : MessagesDataSource {

    /**
     *
     */
    override fun getCardsModelsFactory(
        screenType: String, converterFactory: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> {
        return database.messagesDao().getDataSource(screenType)
            .mapByPage(converterFactory::convert)
    }

    /**
     *
     */
    override fun deleteCachedItems(screenType: String): Unit=
        database.messagesDao().deleteCachedItems(screenType)

    override fun insert(items: List<MessagesEntity>) : Unit=
        database.messagesDao().insert(items)


    override fun insertAndClearCache(items: List<MessagesEntity>, typeScreen: String?): Unit=
        database.messagesDao().insertAndClearCache(items, typeScreen)

}