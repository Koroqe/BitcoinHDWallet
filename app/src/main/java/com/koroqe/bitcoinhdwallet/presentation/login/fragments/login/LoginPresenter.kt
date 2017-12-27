package com.koroqe.bitcoinhdwallet.presentation.login.fragments.login

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import org.bitcoinj.utils.Threading
import java.util.concurrent.Executor
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

        startWallet()
        repository.setAccountExisted(true)
        repository.setWalletFirstLaunch(true)
        viewState.openMainScreen()
    }

    private fun startWallet() {

//        viewState.showProgressBar()
//
//        viewState.hideProgressBar()
    }



    override fun onClickRestoreAccount() {

        viewState.openRestoreScreen()
    }

    fun setupData() {

        if (App.walletKit!!.isRunning) viewState.openMainScreen()
    }
}