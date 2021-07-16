package com.obscura.llc.obscuraproject.di.component

import com.obscura.llc.obscuraproject.di.module.ViewModelModule
import com.obscura.llc.obscuraproject.di.scope.ViewModelScope
import com.obscura.llc.obscuraproject.presentation.activities.auth.AuthActivity
import com.obscura.llc.obscuraproject.presentation.activities.detail.*
import com.obscura.llc.obscuraproject.presentation.activities.location.LocationChooserActivity
import com.obscura.llc.obscuraproject.presentation.activities.main.MainActivity
import com.obscura.llc.obscuraproject.presentation.activities.splash.SplashActivity
import com.obscura.llc.obscuraproject.presentation.activities.welcome.WelcomeActivity
import com.obscura.llc.obscuraproject.presentation.fragments.list_themes.ListThemesFragment
import com.obscura.llc.obscuraproject.presentation.fragments.list_users.ListUsersFragment
import com.obscura.llc.obscuraproject.presentation.fragments.list_messages.ListMessagesFragment
import dagger.Component

@ViewModelScope
@Component(modules = [ViewModelModule::class], dependencies = [InteractorComponent::class])
interface ViewModelComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: WelcomeActivity)
    fun inject(activity: AuthActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(activity: ConversationActivity)
    fun inject(activity: DetailListActivity)
    fun inject(activity: DetailProfileActivity)
    fun inject(activity: DetailFeedActivity)
    fun inject(activity: LocationChooserActivity)
    fun inject(fragment: ListThemesFragment)
    fun inject(fragment: ListUsersFragment)
    fun inject(fragment: ListMessagesFragment)
}