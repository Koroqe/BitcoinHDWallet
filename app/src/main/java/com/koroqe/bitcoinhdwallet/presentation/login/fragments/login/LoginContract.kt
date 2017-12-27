package com.koroqe.bitcoinhdwallet.presentation.login.fragments.login

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 */

interface LoginContract {

    interface View : MvpView {

        fun openMainScreen()
        fun openRestoreScreen()
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Listener {

        fun onClickCreateAccount()

        fun onClickRestoreAccount()
    }
}