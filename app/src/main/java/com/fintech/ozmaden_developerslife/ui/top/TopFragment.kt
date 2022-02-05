package com.fintech.ozmaden_developerslife.ui.top

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel
import com.fintech.ozmaden_developerslife.ui.PostFragment
import com.fintech.ozmaden_developerslife.ui.PostViewModel

class TopFragment : PostFragment() {
    override fun setUpViewModel(): PostViewModel {
        val viewModelFactory = CategoryPostViewModel.Factory("top")
        viewModel = ViewModelProvider(this, viewModelFactory)[CategoryPostViewModel::class.java]
        return viewModel
    }
}