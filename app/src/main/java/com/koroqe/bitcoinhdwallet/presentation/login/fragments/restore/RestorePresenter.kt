package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.bitcoinj.wallet.DeterministicSeed
import java.util.*
import javax.inject.Inject


/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class RestorePresenter : MvpPresenter<RestoreContract.View>(), RestoreContract.Listener {

    @Inject lateinit var repository: Repository

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

        App.walletKit!!.restoreWalletFromSeed(seed)
        if (App.walletKit!!.isRunning) stopKit()
//        startKit()
//        viewState.showProgressBarDownload()
//        addDownloadBlockListener()
    }

    fun startKit() {
        App.walletKit!!.setBlockingStartup(false)
        App.walletKit!!.startAsync()
        App.walletKit!!.awaitRunning()
        repository.setAccountExisted(true)
    }

    fun stopKit() {
        App.walletKit!!.stopAsync()
        App.walletKit!!.awaitTerminated()
    }

    fun addDownloadBlockListener() {

        val listener = object : DownloadProgressTracker() {
            public override fun doneDownload() {
                viewState.goToMainScreen()
            }

            public override fun progress(pct: Double, blocksSoFar: Int, date: Date?) {
                super.progress(pct, blocksSoFar, date)
                viewState.setDownloadProgress(pct)
            }
        }
        App.walletKit!!.peerGroup().addBlocksDownloadedEventListener(listener)
    }
}