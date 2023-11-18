package com.sample.sample.utils

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sample.sample.feature.chat.model.Message
import com.sample.sample.feature.chat.model.MessageList
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SetTextI18n")
@BindingAdapter("order")
fun setOrder(view: AppCompatTextView, value: String?) {
    view.text = "Order $value"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("date")
fun setDate(view: AppCompatTextView, value: Long?) {
    val format = SimpleDateFormat(DATE_FORMAT)
    value?.let {
        view.text = format.format(Date(it))
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("msg")
fun setMessage(view: AppCompatTextView, list: List<Message>?) {
    view.apply {
        visibility = if(list.isNullOrEmpty()){
            View.GONE
        } else {
            text = list.get(0).message
            View.VISIBLE
        }
    }
}

@BindingAdapter("url")
fun setImage(view: AppCompatImageView, logoUrl: String?) {
    val requestOptions = RequestOptions()
        .error(android.R.drawable.sym_action_chat)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

    Glide.with(view.context)
        .load(logoUrl)
        .thumbnail(
            Glide.with(view.context)
                .asDrawable()
                .sizeMultiplier(0.1f)
        ).apply(requestOptions)
        .into(view)
}
