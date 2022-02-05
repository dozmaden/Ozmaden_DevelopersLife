package com.fintech.ozmaden_developerslife.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.fintech.ozmaden_developerslife.R

class GifLoader {
    companion object {
        fun loadImage(gifUrl: String, gifView: ImageView) {
            val safeUrl = gifUrl.replace("http", "https")

            val circularProgress = CircularProgressDrawable(gifView.context)
            circularProgress.apply {
                centerRadius = 50f
                strokeWidth = 10f
                start()
            }

            Glide.with(gifView.context)
                .load(safeUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .apply(
                    RequestOptions()
                        .placeholder(circularProgress)
                        .error(R.drawable.ic_baseline_broken_image_24)
                )
                .into(gifView)
        }
    }
}