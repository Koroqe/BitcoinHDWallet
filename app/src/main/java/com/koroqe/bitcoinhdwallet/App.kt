package com.koroqe.bitcoinhdwallet

import android.app.Application
import com.koroqe.bitcoinhdwallet.di.component.AppComponent
import com.koroqe.bitcoinhdwallet.di.component.DaggerAppComponent
import com.koroqe.bitcoinhdwallet.di.module.ApplicationModule
import com.koroqe.bitcoinhdwallet.wallet.WalletKit
import org.bitcoinj.params.TestNet3Params

/**
 * Created by Koroqe on 12-Dec-17.
 *
 */

class App : Application() {

//    @Inject lateinit var mRepository: Repository

    override fun onCreate() {
        super.onCreate()

        initDagger()
        initWalletKit()
    }

    private fun initDagger() {
        component = DaggerAppComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component?.inject(this)
    }

    private fun initWalletKit() {
        walletKit = WalletKit(networkParams, filesDir, "walletkit")
    }

    override fun onTerminate() {
        walletKit!!.stopAsync()
        walletKit!!.awaitTerminated()
        super.onTerminate()
    }


    companion object {

        val networkParams = TestNet3Params.get()

        @JvmField var walletKit : WalletKit? = null

        var component: AppComponent? = null
            private set
    }
}