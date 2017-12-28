package com.koroqe.bitcoinhdwallet.presentation.login.fragments.login

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
class LoginPresenter : MvpPresenter<LoginContract.View>(), LoginContract.Listener {

    @Inject lateinit var repository: Repository

    init {
        App.component?.inject(this)
    }

    override fun onClickCreateAccount() {

        repository.setWalletFirstLaunch(true)
        viewState.openMainScreen()
    }

    override fun onClickRestoreAccount() {

        viewState.openRestoreScreen()
    }

    fun setupData() {

        if (repository.isAccountExisted()) viewState.openMainScreen()
    }
}