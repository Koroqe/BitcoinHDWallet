package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.wallet.WalletKit
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.bitcoinj.wallet.DeterministicSeed



/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class RestorePresenter : MvpPresenter<RestoreContract.View>(), RestoreContract.Listener {

    private lateinit var repository: Repository
    private lateinit var walletKit: WalletKit

    init {
        App.component?.inject(this)
    }

    override fun onClickRestore(seed: String) {

        var dSeed = DeterministicSeed(
                seed.trim().toLowerCase().split(" "),
                null,
                "",
                viewState.getCurrentTime() as Long
        )
        restoreWalletFromSeed(dSeed)
    }

    fun restoreWalletFromSeed(seed: DeterministicSeed) {

        walletKit.restoreWalletFromSeed(seed)
        if (walletKit.isRunning) {
            stopKit()
        }
        startKit()
        addDownloadBlockListener()
    }

    fun startKit() {
        walletKit.startAsync()
        walletKit.awaitRunning()
    }

    fun stopKit() {
        walletKit.stopAsync()
        walletKit.awaitTerminated()
    }

    fun addDownloadBlockListener() {

        val listener = object : DownloadProgressTracker() {
            public override fun doneDownload() {
                println("blockchain downloaded")
            }
        }
        walletKit.peerGroup().addBlocksDownloadedEventListener(listener)
    }


}