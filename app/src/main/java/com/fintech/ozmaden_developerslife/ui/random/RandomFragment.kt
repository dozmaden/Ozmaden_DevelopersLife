package com.fintech.ozmaden_developerslife.ui.random

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.PostFragment

internal class RandomFragment : PostFragment() {

    override fun viewModel() = ViewModelProvider(this)[RandomPostViewModel::class.java]
}
