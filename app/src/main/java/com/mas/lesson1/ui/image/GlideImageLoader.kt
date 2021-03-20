package com.mas.lesson1.ui.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mas.lesson1.mvp.model.image.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun load(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}