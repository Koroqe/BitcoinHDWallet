package com.koroqe.bitcoinhdwallet.presentation.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.wallet.WalletKit

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class LoginPresenter : MvpPresenter<LoginContract.View>(), LoginContract.Listener {

    private lateinit var repository: Repository
    private lateinit var walletKit: WalletKit

    init {
        App.component?.inject(this)
    }

    override fun onClickCreateAccount() {

        walletKit.startAsync()
        walletKit.awaitRunning()
        viewState.openMainScreen()
    }

    override fun onClickRestoreAccount() {

        viewState.openRestoreScreen()
    }

    fun checkAccountExistance() {

        if (repository.isAccountExisted()) {
            viewState.openMainScreen()
        }
    }
}