package com.koroqe.bitcoinhdwallet.di.module

import android.content.Context
import android.preference.PreferenceManager
import com.koroqe.bitcoinhdwallet.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(application: App)
            = PreferenceManager.getDefaultSharedPreferences(application)

}
