package com.mas.popular_libraries.ui

import android.app.Application
import com.mas.popular_libraries.di.AppComponent
import com.mas.popular_libraries.di.DaggerAppComponent
import com.mas.popular_libraries.di.module.AppModule
import com.mas.popular_libraries.mvp.model.entity.room.db.Database

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}