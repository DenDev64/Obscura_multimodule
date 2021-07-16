package com.obscura.llc.obscuraproject.di.module

import com.obscura.llc.moduleproject.repository.AppRepository
import com.obscura.llc.moduleproject.repository.FeedRepository
import com.obscura.llc.moduleproject.repository.MessagesRepository
import com.obscura.llc.moduleproject.repository.UserRepository
import com.obscura.llc.obscuraproject.di.scope.InteractorScope
import com.obscura.llc.moduleproject.usecases.*
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @InteractorScope
    @Provides
    internal fun providesInteractor(repository: AppRepository): Interactor {
        return Interactor(repository)
    }

    @InteractorScope
    @Provides
    internal fun providesFeedUseCases(repository: FeedRepository): FeedUseCases {
        return FeedUseCasesImpl(repository)
    }

    @InteractorScope
    @Provides
    internal fun providesUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCasesImpl(repository)
    }

    @InteractorScope
    @Provides
    internal fun providesMessagesUseCases(repository: MessagesRepository): MessagesUseCases {
        return MessagesUseCasesImpl(repository)
    }
}