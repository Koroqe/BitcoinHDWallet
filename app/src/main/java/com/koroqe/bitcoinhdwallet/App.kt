package com.koroqe.bitcoinhdwallet

import android.app.Application
import com.koroqe.bitcoinhdwallet.di.component.AppComponent
import com.koroqe.bitcoinhdwallet.di.module.ApplicationModule
import com.koroqe.bitcoinhdwallet.data.Repository
import javax.inject.Inject

/**
 * Created by Koroqe on 12-Dec-17.
 */

class App : Application() {

    @Inject lateinit var repo: Repository

    override fun onCreate() {
        super.onCreate()

        initDagger()

    }

    fun get(): App {
        return applicationContext as App
    }

    private fun initDagger() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component?.inject(this)
    }

    companion object {

        var component: AppComponent? = null
            private set
    }

}