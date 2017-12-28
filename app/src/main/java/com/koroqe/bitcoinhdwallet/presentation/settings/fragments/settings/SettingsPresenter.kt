package com.koroqe.bitcoinhdwallet.presentation.settings.fragments.settings

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
class SettingsPresenter : MvpPresenter<SettingsContract.View>(), SettingsContract.Listener {


    @Inject lateinit var repository : Repository

    init {
        App.component?.inject(this)
    }

    override fun onClickShowSeed() {
        viewState.openShowSeedScreen()
    }

    override fun onClickRestoreAnotherWallet() {
        viewState.openRestoreWalletScreen()
    }
}