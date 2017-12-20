package com.koroqe.bitcoinhdwallet.presentation.main

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 */

interface MainContract {

    interface View : MvpView {

        fun openSendScreen()

        fun openReceiveScreen()

        fun openSettings()

        fun openQRcodeScanner()
    }


    interface Listener {

    }

}