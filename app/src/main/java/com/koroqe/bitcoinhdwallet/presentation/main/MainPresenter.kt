package com.koroqe.bitcoinhdwallet.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class MainPresenter : MvpPresenter<MainContract.View>(), MainContract.Listener {

    private lateinit var repository : Repository

    init {
        App.component?.inject(this)
    }

}