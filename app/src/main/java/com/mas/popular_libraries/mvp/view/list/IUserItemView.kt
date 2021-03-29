package com.mas.popular_libraries.mvp.view.list

interface IUserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}