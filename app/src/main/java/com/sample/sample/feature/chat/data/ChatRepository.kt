package com.sample.sample.feature.chat.data

import javax.inject.Inject

internal class ChatRepository @Inject constructor(
    private val remoteDataSource: ChatRemoteDataSource
): ChatDataSourceContract {
    override suspend fun getChatsFor(username: String?) =
        remoteDataSource.fetchChatsOfUser(username)
}