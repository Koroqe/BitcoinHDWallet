package com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import javax.inject.Inject


/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class ShowSeedPresenter : MvpPresenter<ShowSeedContract.View>(), ShowSeedContract.Listener {

    @Inject lateinit var repository: Repository

    init {
        App.component?.inject(this)
    }

    fun setupData() {
//        App.walletKit!!.awaitRunning()
//        viewState.showCurrentSeed(Utils.join(App.walletKit!!.wallet().keyChainSeed.mnemonicCode))
    }

    override fun onClickOk() {
        viewState.onClickOk()
        repository.setWalletFirstLaunch(false)
    }
}