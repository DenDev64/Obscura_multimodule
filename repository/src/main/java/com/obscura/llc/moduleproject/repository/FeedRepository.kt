package com.obscura.llc.moduleproject.repository

import androidx.paging.DataSource
import com.obscura.llc.moduleproject.data_source.FeedDataSource
import com.obscura.llc.moduleproject.data_source.data.BaseCardModel
import com.obscura.llc.moduleproject.data_source.database.converters.ConverterFactory
import com.obscura.llc.moduleproject.data_source.database.entity.ThemeEntity
import com.obscura.llc.moduleproject.remote_data_source.FeedRemoteDataSource
import com.obscura.llc.moduleproject.utils.MocUtil
import io.reactivex.Completable
import io.reactivex.Single

//todo create abstract parent for Repository

/**
 *
 */
interface FeedRepository {

    /**
     *
     */
    fun fetchFeed(screenType: String): Completable

    /**
     *
     */
    fun fetchNext(screenType: String, lastItemId: String): Completable

    /**
     *
     */
    fun deleteCachedFeed(screenType: String): Completable

    /**
     *
     */
    fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel>
}

/**
 *
 */
class FeedRepositoryImpl(
    private val feedRemoteDataSource: FeedRemoteDataSource,
    private val feedDataSource: FeedDataSource
) : FeedRepository {

    override fun fetchFeed(screenType: String): Completable {
        return feedRemoteDataSource
            .fetchFeed(screenType)
                // todo remove moc logic and add handling error when api logic implemented
            .doOnError {
                val list: List<ThemeEntity> = MocUtil.mocListThemes()
                list.forEach {
                    it.convertItemForDataSource(item = it, isCached = false, screenType = null)
                }
                saveItems(list, false, screenType)
            }
            .flatMapCompletable {
                Completable.fromAction { }
            }
    }

    override fun fetchNext(screenType: String, lastItemId: String): Completable {
        return feedRemoteDataSource
            .fetchNext(screenType, lastItemId)
            // todo remove moc logic and add handling error when api logic implemented
            .flatMap {
                val list: List<ThemeEntity> = MocUtil.mocListThemes()
                list.forEach {
                    it.convertItemForDataSource(item = it, isCached = true, screenType = null)
                }
                Single.just(list)
            }
            .flatMapCompletable {
                Completable.fromAction { saveItems(it, true, screenType) }
            }
    }

    override fun deleteCachedFeed(screenType: String): Completable=
        Completable.fromAction { feedDataSource.deleteCachedFeed(screenType) }


    override fun getCardsFactory(
        screenType: String,
        modelConverter: ConverterFactory
    ): DataSource.Factory<Int, BaseCardModel> =
        feedDataSource.getCardsModelsFactory(screenType, modelConverter)

    private fun saveItems(
        feedItems: List<ThemeEntity>, isCached: Boolean, typeScreen: String?
    ) {
        if (isCached) {
            feedDataSource.insert(feedItems)
        } else {
            feedDataSource.insertAndClearCache(feedItems, typeScreen)
        }
    }

}