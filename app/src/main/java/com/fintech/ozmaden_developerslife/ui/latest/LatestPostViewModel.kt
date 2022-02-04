package com.fintech.ozmaden_developerslife.ui.latest

import com.fintech.ozmaden_developerslife.ui.CategoryPostViewModel

class LatestPostViewModel : CategoryPostViewModel() {
    override fun category(): String {
        return "latest"
    }
}