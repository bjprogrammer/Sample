package com.sample.sample.feature.chat.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class MessageList (
  val id: Int?,
  val title: String?,
  val imageURL: String?,
  val orderId: String?,
  val latestMessageTimestamp : Long?,
  @SerializedName("messageList") val messages: List<Message>?
): Parcelable