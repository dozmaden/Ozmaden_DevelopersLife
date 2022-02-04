package com.fintech.ozmaden_developerslife.ui.top

import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel

class TopPostViewModel : CategoryPostViewModel() {
    override fun category(): String {
        return "top"
    }
}