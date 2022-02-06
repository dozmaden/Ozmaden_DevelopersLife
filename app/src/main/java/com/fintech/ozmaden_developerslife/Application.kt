package com.fintech.ozmaden_developerslife

import android.app.Application
import android.os.Build
import android.provider.ContactsContract
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest

class MyApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
//
//        val progress = CircularProgressDrawable(this)
//        circularProgressDrawable.strokeWidth = 10f
//        circularProgressDrawable.centerRadius = 40f
//        circularProgressDrawable.start()

//        val progress = ShimmerDrawable()

        return ImageLoader.Builder(this)
//            .placeholder(progress)
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
