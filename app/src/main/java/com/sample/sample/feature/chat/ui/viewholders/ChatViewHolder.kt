package com.sample.sample.feature.chat.ui.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR
import com.sample.sample.feature.chat.model.Message

class ChatViewHolder(private val viewBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(obj: Message) {
        viewBinding.apply {
            setVariable(BR.message, obj)
            executePendingBindings()
        }
    }
}