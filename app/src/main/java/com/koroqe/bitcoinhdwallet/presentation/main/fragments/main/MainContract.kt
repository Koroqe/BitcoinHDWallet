package com.koroqe.bitcoinhdwallet.presentation.main.fragments.main

import com.arellomobile.mvp.MvpView
import org.bitcoinj.wallet.WalletTransaction

/**
 * Created by Koroqe on 14-Dec-17.
 */

interface MainContract {

    interface View : MvpView {

        fun hideDownloadProgressBar()
        fun setDownloadProgress(progress : Int)
        fun openSendScreen()
        fun openReceiveScreen()
        fun openSettings()
        fun openQRcodeScanner()
        fun showSeedForBackup()
        fun showMessageDownloading()
        fun updateBalance(balance: String)
        fun updateRecycler(walletTransactions: Iterable<WalletTransaction>)
    }

    interface Listener {

        fun onClickSettings()
        fun onClickQRcode()
        fun onClickSend()
        fun onClickReceive()
    }

}