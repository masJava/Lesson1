package com.mas.popular_libraries.di.module

import com.mas.popular_libraries.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App = app


}