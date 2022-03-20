package com.example.demoarchitectcomponent.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application?) {


    @Provides
    @Singleton
    fun provideApplication(): Application? {
        return application
    }
}