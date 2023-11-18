package com.sample.sample.feature.chat.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Message (
  val messageId: String?,
  val message: String?,
  val timestamp: Long?,
  val sender: String? = "USER",
  val messageType: String? = "text"
): Parcelable