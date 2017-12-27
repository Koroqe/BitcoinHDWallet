package com.koroqe.bitcoinhdwallet.presentation.qrcode

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import javax.inject.Inject

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class QrPresenter : MvpPresenter<QrContract.View>(), QrContract.Listener {


    @Inject lateinit var repository : Repository

    init {
        App.component?.inject(this)
    }

}