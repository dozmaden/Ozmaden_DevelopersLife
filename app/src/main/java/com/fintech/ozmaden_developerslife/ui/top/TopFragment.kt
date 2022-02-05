package com.fintech.ozmaden_developerslife.ui.top

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.PostFragment

class TopFragment : PostFragment() {
    override fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this)[TopPostViewModel::class.java]
    }
}