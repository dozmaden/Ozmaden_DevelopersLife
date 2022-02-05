package com.fintech.ozmaden_developerslife.ui.latest

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel
import com.fintech.ozmaden_developerslife.ui.PostFragment
import com.fintech.ozmaden_developerslife.ui.PostViewModel

class LatestFragment : PostFragment() {
    override fun setUpViewModel(): PostViewModel {
        val viewModelFactory = CategoryPostViewModel.Factory( "latest")
        viewModel = ViewModelProvider(this, viewModelFactory)[CategoryPostViewModel::class.java]
        Log.d("LatestFragment", (viewModel as CategoryPostViewModel).category)
        return viewModel
    }
}