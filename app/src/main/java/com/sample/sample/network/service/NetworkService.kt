package com.sample.sample.network.service

import com.sample.sample.feature.chat.model.MessageList
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {
    @GET("{username}/chat/chats")
    suspend fun getChatList(@Path("username") username: String?): Response<List<MessageList>>
}