package com.koroqe.bitcoinhdwallet

import android.app.Application
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.di.component.AppComponent
import com.koroqe.bitcoinhdwallet.di.component.DaggerAppComponent
import com.koroqe.bitcoinhdwallet.di.component.HasComponent
import com.koroqe.bitcoinhdwallet.di.module.ApplicationModule
import com.koroqe.bitcoinhdwallet.wallet.WalletKit
import javax.inject.Inject

/**
 * Created by Koroqe on 12-Dec-17.
 *
 */

class App : Application(), HasComponent<AppComponent?> {

    @Inject lateinit var mRepository: Repository
    @Inject lateinit var mWalletAppKit: WalletKit

    override fun onCreate() {
        super.onCreate()

        initDagger()
//        initWalletAppKit()
    }

    fun get(): App {
        return applicationContext as App
    }

    private fun initDagger() {
        component = DaggerAppComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component?.inject(this)
    }

//    private fun initWalletAppKit() {
//        mWalletAppKit.startAsync()
//        mWalletAppKit.awaitRunning()
//    }

    companion object {

        val networkType : String = "test"
        var component: AppComponent? = null
            private set
    }

    override fun getComponent() : AppComponent? {
        return component
    }
}