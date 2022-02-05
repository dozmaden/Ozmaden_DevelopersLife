package com.fintech.ozmaden_developerslife.ui.latest

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel
import com.fintech.ozmaden_developerslife.ui.PostFragment
import com.fintech.ozmaden_developerslife.ui.PostViewModel

internal class LatestFragment : PostFragment() {
    override fun setUpViewModel(): PostViewModel {
        val viewModelFactory = CategoryPostViewModel.Factory("latest")
        viewModel = ViewModelProvider(this, viewModelFactory)[CategoryPostViewModel::class.java]
        return viewModel
    }
}
