package com.koroqe.bitcoinhdwallet.presentation.main.fragments.send

import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.SendContract

/**
 * Created by Koroqe on 13-Dec-17.
 */

class SendPresenter : SendContract.Listener {

    private lateinit var repo : Repository

    init {
        App.component?.inject(this)
    }

    fun sendFunds() {

    }

    fun updateBalance() {

    }

    override fun onClickSend(address : String, amount : String) {

    }

    override fun onClickQQCode() {

    }

}