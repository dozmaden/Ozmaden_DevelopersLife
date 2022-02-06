package com.fintech.ozmaden_developerslife.ui.hot

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel
import com.fintech.ozmaden_developerslife.ui.PostFragment

private const val CATEGORY = "hot"

internal class HotFragment : PostFragment() {

    override fun viewModel() =
        CategoryPostViewModel.Factory(CATEGORY).let { factory ->
            ViewModelProvider(this, factory)[CategoryPostViewModel::class.java]
        }
}
