package com.obscura.llc.moduleproject.utils.di

import com.google.gson.Gson
import com.obscura.llc.moduleproject.utils.ApplicationUtils
import com.obscura.llc.moduleproject.utils.preference.PreferencesManager
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    val applicationUtils: ApplicationUtils
    val preferencesManager: PreferencesManager
    val gson: Gson
}