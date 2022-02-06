package com.fintech.ozmaden_developerslife.ext

import android.view.View

internal var View.isVisible: Boolean
    set(isVisible: Boolean) {
        visibility =
            if (isVisible) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }
    get() = visibility == View.VISIBLE
