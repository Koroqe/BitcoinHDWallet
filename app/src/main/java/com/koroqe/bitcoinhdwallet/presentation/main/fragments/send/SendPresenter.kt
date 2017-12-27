package com.koroqe.bitcoinhdwallet.presentation.main.fragments.send

import android.text.TextUtils
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.SendContract
import org.bitcoinj.core.Address
import org.bitcoinj.core.Coin
import org.bitcoinj.core.InsufficientMoneyException
import org.bitcoinj.wallet.SendRequest

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class SendPresenter : MvpPresenter<SendContract.View>(), SendContract.Listener {

    private lateinit var repository : Repository

    init {
        App.component?.inject(this)
    }

    fun sendFunds(address : String, amount : String) {

        if (TextUtils.isEmpty(address)) {
            viewState.showMessageSelectRecepient()
            return
        }
        if (TextUtils.isEmpty(amount) or (java.lang.Double.parseDouble(amount) <= 0)) {
            viewState.showMessageEnterAmount()
            return
        }
        if (App.walletKit!!.wallet().balance.isLessThan(Coin.parseCoin(amount))) {
            viewState.showMessageNotEnoughFunds()
            viewState.resetAmount()
            return
        }

        val request = SendRequest.to(Address.fromBase58(App.networkParams, address), Coin.parseCoin(amount))
        try {
            App.walletKit!!.wallet().completeTx(request)
            App.walletKit!!.wallet().commitTx(request.tx)
            App.walletKit!!.peerGroup().broadcastTransaction(request.tx).broadcast()
        } catch (e: InsufficientMoneyException) {
            e.printStackTrace()
            viewState.showError(e.message)
        }
    }

    fun updateBalance() {

    }

    override fun onClickSend(address : String, amount : String) {

        sendFunds(address, amount)
    }

    override fun onClickQRCode() {

    }

}