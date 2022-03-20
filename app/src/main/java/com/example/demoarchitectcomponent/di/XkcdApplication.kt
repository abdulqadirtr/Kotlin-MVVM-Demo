package com.example.demoarchitectcomponent.di

import android.app.Application

class XkcdApplication() : Application() {

    //extend because we use it on application level
   lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}