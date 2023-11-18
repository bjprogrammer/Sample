package com.sample.sample.feature.chat.data

import com.sample.sample.feature.chat.model.MessageList
import retrofit2.Response

internal interface ChatDataSourceContract {
    suspend fun getChatsFor(username: String?): Response<List<MessageList>>
}