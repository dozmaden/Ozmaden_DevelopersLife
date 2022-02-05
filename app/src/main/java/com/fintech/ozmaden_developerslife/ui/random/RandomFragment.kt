package com.fintech.ozmaden_developerslife.ui.random

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.PostFragment
import com.fintech.ozmaden_developerslife.ui.PostViewModel

class RandomFragment : PostFragment() {
    override fun setUpViewModel(): PostViewModel {
        viewModel =
            ViewModelProvider(this)[RandomPostViewModel::class.java]
        return viewModel
    }
}