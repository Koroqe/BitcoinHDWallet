package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

interface RestoreContract {

    interface Listener {

        fun onClickRestore(seed : String)
    }

    interface View : MvpView {

        fun restore()

        fun showErrorUnknownSeed()

        fun getCurrentTime()
        fun showProgressBarDownload()
        fun hideProgressBarDownload()
    }
}