package com.obscura.llc.obscuraproject.di.module

import android.app.Application
import com.obscura.llc.moduleproject.usecases.FeedUseCases
import com.obscura.llc.moduleproject.usecases.Interactor
import com.obscura.llc.moduleproject.usecases.MessagesUseCases
import com.obscura.llc.moduleproject.usecases.UserUseCases
import com.obscura.llc.obscuraproject.di.scope.ViewModelScope
import com.obscura.llc.obscuraproject.domain.*
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(app: com.obscura.llc.obscuraproject.App) {

    internal var app: Application = app

    @ViewModelScope
    @Provides
    internal fun providesAllUserViewModel(interactor: Interactor): AllUsersViewModel {
        return AllUsersViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesSingleUserViewModel(interactor: Interactor): SingleUserViewModel {
        return SingleUserViewModel(app, interactor)
    }

    @ViewModelScope
    @Provides
    internal fun providesFeedViewModel(feedUseCases: FeedUseCases): FeedViewModel {
        return FeedViewModel(feedUseCases)
    }

    @ViewModelScope
    @Provides
    internal fun providesUserViewModel(userUseCases: UserUseCases): UserViewModel {
        return UserViewModel(userUseCases)
    }

    @ViewModelScope
    @Provides
    internal fun providesMessagesViewModel(messagesUseCases: MessagesUseCases): MessagesViewModel {
        return MessagesViewModel(messagesUseCases)
    }
}