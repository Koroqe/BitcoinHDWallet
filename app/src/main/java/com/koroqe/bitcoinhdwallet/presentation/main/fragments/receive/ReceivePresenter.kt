package com.koroqe.bitcoinhdwallet.presentation.main.fragments.receive

import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.ReceiveContract

/**
 * Created by Koroqe on 13-Dec-17.
 */

class ReceivePresenter : ReceiveContract.Listener {

    private lateinit var repo : Repository

    init {
        App.component?.inject(this)
    }

    fun updateQRcode() {

    }

    override fun onClickCopyToClipboard(address : String) {

    }

}