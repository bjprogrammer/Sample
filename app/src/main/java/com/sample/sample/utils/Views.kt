package com.sample.sample.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.constraintlayout.widget.Group

fun View.throttleClick(isAnimated: Boolean = false, debounceTime: UInt = 300u, action: () -> Unit) {

    fun animation() {
        val animX = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 0.9f, 1.0f)
        val animY = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.9f, 1.0f)
        val animator = AnimatorSet()
        animator.play(animX).with(animY)
        animator.start()
    }

    setOnClickListener {
        when {
            tag != null && (tag as Long) > System.currentTimeMillis() -> return@setOnClickListener
            else -> {
                tag = System.currentTimeMillis() + debounceTime.toLong()
                if (isAnimated) animation()
                action()
            }
        }
    }
}

fun Group.setAllOnClickListener(action: () -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).throttleClick(true,500u, action)
    }
}