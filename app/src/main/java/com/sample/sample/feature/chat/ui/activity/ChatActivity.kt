package com.sample.sample.feature.chat.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.sample.sample.R
import com.sample.sample.databinding.ActivityChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        setupViews()
    }

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).findNavController()
    }

    private fun setupViews() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }

    //Back button tracking through Nav Graph
    override fun onSupportNavigateUp() =
        navController.navigateUp()
}