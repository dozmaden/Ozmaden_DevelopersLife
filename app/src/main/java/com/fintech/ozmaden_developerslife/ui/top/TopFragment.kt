package com.fintech.ozmaden_developerslife.ui.top

import androidx.lifecycle.ViewModelProvider
import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel
import com.fintech.ozmaden_developerslife.ui.PostFragment

private const val CATEGORY = "top"

internal class TopFragment : PostFragment() {

    override fun viewModel() =
        CategoryPostViewModel.Factory(CATEGORY).let { factory ->
            ViewModelProvider(this, factory)[CategoryPostViewModel::class.java]
        }
}
