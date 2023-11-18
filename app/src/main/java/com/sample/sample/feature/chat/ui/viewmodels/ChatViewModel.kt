package com.sample.sample.feature.chat.ui.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.sample.feature.chat.data.usecases.GetChatUsecase
import com.sample.sample.feature.chat.model.MessageList
import com.sample.sample.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("CheckResult")
internal class ChatViewModel @Inject constructor(
    private val getChatUsecase: GetChatUsecase
) : ViewModel(){
    private val _liveData = MutableLiveData<Result<List<MessageList>>>()
    val liveData: LiveData<Result<List<MessageList>>> = _liveData

    fun getChatHistory()  {
        viewModelScope.launch(Dispatchers.IO) {
            val chatResponse = getChatUsecase.invoke("codebuds-fk")
            val result = if (chatResponse.isSuccessful) {
                chatResponse.body()?.let {
                    Result.Success(it)
                } ?: run {
                    Result.Error(chatResponse.message())
                }
            } else {
                Result.Error(chatResponse.errorBody()?.string())
            }
            _liveData.postValue(result)
        }
    }
}
