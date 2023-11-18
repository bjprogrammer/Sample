package com.sample.sample.feature.chat.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.sample.R
import com.sample.sample.feature.chat.model.MessageList
import com.sample.sample.feature.chat.ui.viewholders.OrderViewHolder

class OrderAdapter constructor(
    private  val onClick:(list: MessageList) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<MessageList> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding:ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_order, parent,
            false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? OrderViewHolder)?.bind(data[position], onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateList(data: List<MessageList>) {
        val diffCallback = DiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data.clear()
        this.data.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}

class DiffCallback(private val oldList: List<MessageList>, private val newList: List<MessageList>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }
}