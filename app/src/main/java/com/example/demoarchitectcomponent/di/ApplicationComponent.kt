package com.example.demoarchitectcomponent.di

import android.app.Application
import com.example.demoarchitectcomponent.MainActivity
import com.example.demoarchitectcomponent.fragments.XkcdMainFragment
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

//use singleton here because of scope methods
// we should always define modules inside components

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent{

    fun inject(mainActivity: MainActivity)
    fun inject(xkcdMainFragment: XkcdMainFragment)
}