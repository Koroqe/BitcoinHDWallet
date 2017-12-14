package com.koroqe.bitcoinhdwallet.presentation.main

import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository

/**
 * Created by Koroqe on 13-Dec-17.
 */

class MainPresenter {

    private lateinit var repo : Repository

    init {
        App.component?.inject(this)
    }

}