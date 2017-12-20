package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

/**
 * Created by Koroqe on 14-Dec-17.
 */

internal interface SendContract {

    interface View {

        fun openQRscanner()

        fun updateBalance()
    }

    interface Listener {

        fun onClickSend(address : String, amount : String)

        fun onClickQQCode()
    }
}