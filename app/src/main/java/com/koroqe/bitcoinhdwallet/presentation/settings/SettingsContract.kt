package com.koroqe.bitcoinhdwallet.presentation.settings

import com.arellomobile.mvp.MvpView

/**
 * Created by Koroqe on 14-Dec-17.
 */

interface SettingsContract {

    interface View : MvpView {

        fun openShowSeedScreen()
        fun openRestoreWalletScreen()
    }

    interface Listener {

        fun onClickShowSeed()
        fun onClickRestoreAnotherWallet()
    }
}