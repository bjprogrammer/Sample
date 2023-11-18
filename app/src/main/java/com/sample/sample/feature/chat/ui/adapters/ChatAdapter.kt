package com.sample.sample.feature.chat.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.sample.R
import com.sample.sample.feature.chat.model.Message
import com.sample.sample.feature.chat.ui.viewholders.ChatViewHolder

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<Message> = arrayListOf()
    private val botType = 0
    private val userType = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewDataBinding = when(viewType) {
            botType -> {
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_reciever, parent,
                    false
                )
            }
            else -> {
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_sender, parent,
                    false
                )
            }
        }
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ChatViewHolder)?.bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position].sender) {
           "BOT" -> botType
           else -> userType
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(data: List<Message>) {
        val diffCallback = DiffChatCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data.clear()
        this.data.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}

class DiffChatCallback(private val oldList: List<Message>, private val newList: List<Message>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].messageId == newList[newItemPosition].messageId
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }
}