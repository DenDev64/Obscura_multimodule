package com.obscura.llc.obscuraproject.di.component

import com.obscura.llc.obscuraproject.di.module.InteractorModule
import com.obscura.llc.obscuraproject.di.scope.InteractorScope
import com.obscura.llc.moduleproject.repository.di.RepositoryComponent
import com.obscura.llc.moduleproject.usecases.FeedUseCases
import com.obscura.llc.moduleproject.usecases.Interactor
import com.obscura.llc.moduleproject.usecases.MessagesUseCases
import com.obscura.llc.moduleproject.usecases.UserUseCases
import dagger.Component

@InteractorScope
@Component(modules = [InteractorModule::class], dependencies = [RepositoryComponent::class])
interface InteractorComponent {
    val interactor: Interactor
    val feedUseCases : FeedUseCases
    val userUseCases : UserUseCases
    val messagesUseCases : MessagesUseCases
}