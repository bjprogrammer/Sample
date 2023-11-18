package com.sample.sample.feature.chat.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sample.sample.R
import com.sample.sample.databinding.FragmentChildBinding
import com.sample.sample.feature.chat.model.Message
import com.sample.sample.feature.chat.model.MessageList
import com.sample.sample.feature.chat.ui.adapters.ChatAdapter
import com.sample.sample.feature.chat.ui.fragments.OrderFragment.Companion.MESSAGES
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ChildFragment : Fragment() {
    lateinit var binding: FragmentChildBinding
    private var messageList: MessageList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messageList = arguments?.getParcelable(MESSAGES)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_child, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.order = messageList
        val chatAdapter = ChatAdapter().apply {
            binding.msgList.adapter = this
            messageList?.messages?.let { updateList( it) }
        }

        binding.btnSend.setOnClickListener {
            val msg = binding.etMsg.text.toString()
            if(msg.isNotEmpty()){
                val message = Message(
                    Random(10000).nextInt().toString(),
                    msg,
                    System.currentTimeMillis()
                )
                val list = messageList?.messages?.toMutableList()?: arrayListOf()
                list.add(message)
                chatAdapter.updateList(list)
                binding.etMsg.setText("")
            }
        }
    }
}