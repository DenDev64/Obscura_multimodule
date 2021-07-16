package com.obscura.llc.moduleproject.repository.di

import com.obscura.llc.moduleproject.repository.AppRepository
import com.obscura.llc.moduleproject.repository.FeedRepository
import com.obscura.llc.moduleproject.repository.MessagesRepository
import com.obscura.llc.moduleproject.repository.UserRepository
import com.obscura.llc.moduleproject.data_source.di.DatabaseComponent
import com.obscura.llc.moduleproject.remote_data_source.di.ApiComponent
import dagger.Component

@RepositoryScope
@Component(modules = [RepositoryModule::class], dependencies = [ApiComponent::class, DatabaseComponent::class])
interface RepositoryComponent {
    val repository: AppRepository
    val feedRepository: FeedRepository
    val userRepository: UserRepository
    val messagesRepository: MessagesRepository
}