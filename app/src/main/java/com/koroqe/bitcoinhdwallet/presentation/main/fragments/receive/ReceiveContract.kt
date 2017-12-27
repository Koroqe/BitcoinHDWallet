package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 */

interface ReceiveContract {

    interface View : MvpView {

        fun showMessageAdressCopied()
        fun copyToClipboard(address: String)
        fun setReceiveAddress(receiveAddress: String?)
    }

    interface Listener {

        fun onClickCopyToClipboard(address : String)
    }
}