package com.koroqe.bitcoinhdwallet.presentation.main.fragments.receive

import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.ReceiveContract

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class ReceivePresenter : MvpPresenter<ReceiveContract.View>(), ReceiveContract.Listener {

    private lateinit var repo : Repository

    init {
        App.component?.inject(this)
    }

    override fun onClickCopyToClipboard(address : String) {

        viewState.copyToClipboard(address)
        viewState.showMessageAdressCopied()
    }

    fun setupData() {

        updateAdress()
    }

    fun updateAdress() {

        val receiveAddress = App.walletKit!!.wallet().freshReceiveAddress().toBase58()
        viewState.setReceiveAddress(receiveAddress)
    }

    fun getReceiveAdress(): String = App.walletKit!!.wallet().freshReceiveAddress().toBase58()
}