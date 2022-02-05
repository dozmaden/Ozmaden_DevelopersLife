package com.fintech.ozmaden_developerslife.ui.latest

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.PostFragment

class LatestFragment : PostFragment() {
    override fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this)[LatestPostViewModel::class.java]
    }
}