package com.sample.sample.feature.chat.di

import com.sample.sample.feature.chat.data.ChatDataSourceContract
import com.sample.sample.feature.chat.data.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {
    @Binds
    abstract fun provideChatDataSource(repository: ChatRepository): ChatDataSourceContract
}