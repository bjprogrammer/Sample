package com.sample.sample.feature.chat.ui.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR
import com.sample.sample.feature.chat.model.MessageList

class OrderViewHolder(private val viewBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(obj: MessageList, onClick:(list:MessageList) -> Unit) {
        viewBinding.apply {
            setVariable(BR.order, obj)
            executePendingBindings()
            viewBinding.root.setOnClickListener {
                onClick.invoke(obj)
            }
        }
    }
}