package com.fintech.ozmaden_developerslife

import android.app.Application
import android.os.Build
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy

class MyApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 40f
        circularProgressDrawable.start()

        return ImageLoader.Builder(this)
            .placeholder(circularProgressDrawable)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@MyApplication))
                } else {
                    add(GifDecoder())
                }
            }
            .build()
    }
}
