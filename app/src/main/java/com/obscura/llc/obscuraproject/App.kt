package com.obscura.llc.obscuraproject

import androidx.room.Room
import android.content.Context
import android.os.AsyncTask
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.obscura.llc.obscuraproject.di.module.InteractorModule
import com.obscura.llc.obscuraproject.di.component.*
import com.obscura.llc.obscuraproject.di.module.*
import com.obscura.llc.moduleproject.data_source.database.AppDatabase
import com.obscura.llc.moduleproject.data_source.database.entity.*
import com.obscura.llc.moduleproject.data_source.di.DaggerDatabaseComponent
import com.obscura.llc.moduleproject.data_source.di.DatabaseModule
import com.obscura.llc.moduleproject.remote_data_source.di.ApiModule
import com.obscura.llc.moduleproject.remote_data_source.di.DaggerApiComponent
import com.obscura.llc.moduleproject.repository.di.DaggerRepositoryComponent
import com.obscura.llc.moduleproject.repository.di.RepositoryModule
import com.obscura.llc.moduleproject.utils.MocUtil
import com.obscura.llc.moduleproject.utils.di.AppModule
import com.obscura.llc.moduleproject.utils.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 *
 */
class App: MultiDexApplication() {

    private var viewModelComponent: ViewModelComponent? = null
    private var database: AppDatabase? = null
    private var refWatcher: RefWatcher? = null

    init {
        com.obscura.llc.obscuraproject.App.Companion.applicationInstance= this
    }

    companion object {
        private lateinit var applicationInstance: com.obscura.llc.obscuraproject.App
        private val LEAK_CANARY_ENABLED = true

        @JvmStatic
        fun getAppContext(): Context {
            return com.obscura.llc.obscuraproject.App.Companion.applicationInstance.applicationContext
        }

        fun get(): com.obscura.llc.obscuraproject.App {
            return com.obscura.llc.obscuraproject.App.Companion.applicationInstance.applicationContext as com.obscura.llc.obscuraproject.App
        }

        fun getRefWatcher(): RefWatcher {
            return com.obscura.llc.obscuraproject.App.Companion.get().refWatcher!!
        }
//todo refactor using ApplicationUtils
        fun isLeakCanaryEnabled(): Boolean {
            return (BuildConfig.DEBUG && com.obscura.llc.obscuraproject.App.Companion.LEAK_CANARY_ENABLED
                    && BuildConfig.APPLICATION_ID.equals(
                "com.obscura.llc.defaultproject"
            ))
        }

        fun getContext(): Context =
            com.obscura.llc.obscuraproject.App.Companion.getContext()
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
        initRoom()
        initDagger()
    }

    private fun initRoom() {
        // todo don't use allowMainThreadQueries()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "defaultproject_database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration() //TODO: dont know if this is a good practise
            .build()
    }

    private fun initDagger() {
        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        val apiComponent = DaggerApiComponent.builder()
            .appComponent(appComponent)
            .apiModule(ApiModule())
            .build()

        val databaseComponent = DaggerDatabaseComponent.builder()
            .databaseModule(DatabaseModule(this!!.database!!))
            .build()

        val repositoryComponent = DaggerRepositoryComponent.builder()
            .apiComponent(apiComponent)
            .databaseComponent(databaseComponent)
            .repositoryModule(RepositoryModule())
            .build()

        val interactorComponent = DaggerInteractorComponent.builder()
            .repositoryComponent(repositoryComponent)
            .interactorModule(InteractorModule())
            .build()

        viewModelComponent = DaggerViewModelComponent.builder()
            .interactorComponent(interactorComponent)
            .viewModelModule(ViewModelModule(this))
            .build()
    }

    //todo need refactor
    fun getViewModelComponent(): ViewModelComponent {
        return this.viewModelComponent!!
    }

    /**
     *
     */
    fun saveMockToDatabase(){
        AsyncTask.execute {
            val listThemes: List<ThemeEntity> = MocUtil.mocListThemes()
            listThemes.forEach {
                it.convertItemForDataSource(item = it, isCached = false, screenType = null)
            }
            database?.themeDao()?.insert(listThemes)
            val listUsers: List<UserEntity> = MocUtil.mocListUsers()
            listUsers.forEach {
                it.convertItemForDataSource(item = it, isCached = false, screenType = null)
            }
            database?.userDao()?.insert(listUsers)
            val listMessages: List<MessagesEntity> = MocUtil.mocListMessages()
            listMessages.forEach {
                it.convertItemForDataSource(item = it, isCached = false, screenType = null)
            }
            database?.messagesDao()?.insert(listMessages)
        }
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        if (com.obscura.llc.obscuraproject.App.Companion.isLeakCanaryEnabled()) {
            refWatcher = LeakCanary.install(this)
        }
    }
}