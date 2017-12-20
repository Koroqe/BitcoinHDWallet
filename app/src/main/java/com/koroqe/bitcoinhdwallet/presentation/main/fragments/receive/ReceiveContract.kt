package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

/**
 * Created by Koroqe on 14-Dec-17.
 */

internal interface ReceiveContract {

    interface View {
        fun copyToClipboard()

        fun updateQRImage()
    }

    interface Listener {

        fun onClickCopyToClipboard(address : String)
    }


}