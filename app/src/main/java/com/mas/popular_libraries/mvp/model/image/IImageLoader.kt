package com.mas.popular_libraries.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}