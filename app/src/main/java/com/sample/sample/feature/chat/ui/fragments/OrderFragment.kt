package com.sample.sample.feature.chat.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sample.sample.R
import com.sample.sample.databinding.FragmentOrderBinding
import com.sample.sample.feature.chat.model.MessageList
import com.sample.sample.feature.chat.ui.viewmodels.ChatViewModel
import com.sample.sample.feature.chat.ui.adapters.OrderAdapter
import com.sample.sample.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class OrderFragment: Fragment(){
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var binding: FragmentOrderBinding
    private val chatViewModel: ChatViewModel by viewModels()
    private var list: List<MessageList> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_order, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupViews()
        observeViewModel(chatViewModel)
    }

    @SuppressLint("SetTextI18n")
    fun FragmentOrderBinding.setupViews() {
        orderAdapter = OrderAdapter {
            Bundle().apply {
                putParcelable(MESSAGES, it)
                findNavController().navigate(R.id.action_navigate_to_child, this)
            }
        }
        orderList.adapter = orderAdapter
        progressBar.visibility = View.VISIBLE
        chatViewModel.getChatHistory()

        etFilter.doOnTextChanged { text, _, _, _ ->
            filter(text.toString())
        }
    }

    private fun observeViewModel(vm: ChatViewModel) {
        vm.liveData.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            if (it is Result.Success) {
               list = it.data
               orderAdapter.updateList(list)
            } else if (it is Result.Error){
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun filter(text: String){
        val temp: MutableList<MessageList> =  arrayListOf()
        if(text.isEmpty()) {
            temp.addAll(list)
        } else {
            for(item in list){
                if(item.orderId?.contains(text) == true || item.title?.contains(text) == true){
                    temp.add(item)
                }
            }
        }
        orderAdapter.updateList(temp)
    }

    companion object {
        const val MESSAGES = "messages"
    }
}