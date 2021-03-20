package com.mas.lesson1.mvp.model.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}