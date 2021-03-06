package com.fintech.ozmaden_developerslife

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.fintech.ozmaden_developerslife.dto.Post

internal class GifLoader {
    companion object {
        fun loadImage(img: ImageView, post: Post) {
            post.let {
                val progressCircle = CircularProgressDrawable(img.context)
                progressCircle.strokeWidth = 10f
                progressCircle.centerRadius = 40f
                progressCircle.start()

                Glide.with(img.context)
                    .load(post.gifURL)
                    .thumbnail(Glide.with(img.context).load(post.previewURL))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(
                        RequestOptions()
                            .placeholder(progressCircle)
                            .error(R.drawable.ic_baseline_broken_image_24)
                    )
                    .into(img)
            }
        }
    }
}
