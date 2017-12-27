package com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

interface ShowSeedContract {

    interface Listener {

        fun onClickOk()
    }

    interface View : MvpView {

        fun showCurrentSeed(keyChainSeed: String)
        fun onClickOk()
    }
}