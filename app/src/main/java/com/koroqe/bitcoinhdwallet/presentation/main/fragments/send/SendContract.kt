package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 */

interface SendContract {

    interface View : MvpView {

        fun openQRscanner()
        fun showError(message: String?)
        fun showMessageSelectRecepient()
        fun showMessageEnterAmount()
        fun showMessageNotEnoughFunds()
        fun resetAmount()
    }

    interface Listener {

        fun onClickSend(address : String, amount : String)
        fun onClickQRCode()
    }
}