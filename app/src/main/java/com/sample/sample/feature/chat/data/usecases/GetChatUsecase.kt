package com.sample.sample.feature.chat.data.usecases

import com.sample.sample.feature.chat.data.ChatDataSourceContract
import javax.inject.Inject

internal class GetChatUsecase @Inject constructor(
    private val dataSource: ChatDataSourceContract
) {
    suspend fun invoke(userName: String) = dataSource.getChatsFor(userName)
}