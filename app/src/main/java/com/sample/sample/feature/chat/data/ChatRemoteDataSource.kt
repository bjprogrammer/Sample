package com.sample.sample.feature.chat.data

import com.sample.sample.network.service.NetworkService
import javax.inject.Inject

internal class ChatRemoteDataSource @Inject constructor(
    private val networkService: NetworkService
){
    suspend fun fetchChatsOfUser(username: String?) =
        networkService.getChatList(username)
}