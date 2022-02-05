package com.fintech.ozmaden_developerslife.ui.random

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.PostFragment

class RandomFragment : PostFragment() {
    override fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this)[RandomPostViewModel::class.java]
    }
}