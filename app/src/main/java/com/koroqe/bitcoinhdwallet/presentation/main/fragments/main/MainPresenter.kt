package com.koroqe.bitcoinhdwallet.presentation.main.fragments.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.wallet.WalletKit
import org.bitcoinj.core.listeners.DownloadProgressTracker
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import javax.inject.Inject


/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

@InjectViewState
class MainPresenter : MvpPresenter<MainContract.View>(), MainContract.Listener, TxItemClickListener {

    @Inject lateinit var repository: Repository

    private var isDownloading = false

    init {
        App.component?.inject(this)
    }

    override fun onClickSettings() {
        viewState.openSettings()
    }

    override fun onClickQRcode() {
        if (isDownloading) {
            showMessageIfDownloading()
            return
        }
        viewState.openQRcodeScanner()
    }

    override fun onClickSend() {
        if (isDownloading) {
            showMessageIfDownloading()
            return
        }
        viewState.openSendScreen()
    }

    override fun onClickReceive() {
        if (isDownloading) {
            showMessageIfDownloading()
            return
        }
        viewState.openReceiveScreen()
    }

    override fun onClickTx(position: Int) {

    }

    fun setupData() {

        setupWalletKit()
        showSeedIfFirstLaunch()
    }

    fun setupWalletKit() {

        if (App.walletKit!!.isRunning) {
            addDownloadListener()
            addCoinsChangedListener()
            viewState.updateBalance(App.walletKit!!.wallet().balance.toFriendlyString())
            viewState.updateRecycler(App.walletKit!!.wallet().walletTransactions)
            return
        }
        addSetupCompletedListener()
        isDownloading = true
        App.walletKit!!.setAutoSave(true)
        App.walletKit!!.setBlockingStartup(false)
        App.walletKit!!.startAsync()
        App.walletKit!!.awaitRunning()
    }

    private fun showSeedIfFirstLaunch() {

        if (repository.isFirstLaunch()) {
            viewState.showSeedForBackup()
        }
    }

    private fun addDownloadListener() {
        val listener = object : DownloadProgressTracker() {

            override fun startDownload(blocks: Int) {

                super.startDownload(blocks)
                isDownloading = true
            }

            public override fun doneDownload() {

                doAsync {
                    uiThread {
                        updateStats()
                        viewState.hideDownloadProgressBar()
                    }
                }
                isDownloading = false
            }

            public override fun progress(pct: Double, blocksSoFar: Int, date: Date?) {
                super.progress(pct, blocksSoFar, date)

                doAsync { uiThread { viewState.setDownloadProgress(pct.toInt()) } }
                isDownloading = true
            }
        }
        App.walletKit!!.setDownloadListener(listener)
    }

    fun addSetupCompletedListener() {

        val listener = object : WalletKit.SetupCompletedListener() {
            override fun onSetupCompleted() {
                addDownloadListener()
                addCoinsChangedListener()
                repository.setAccountExisted(true)
            }
        }
        App.walletKit!!.setupCompletedListener = listener
    }


    private fun addCoinsChangedListener() {

        App.walletKit!!.wallet().addCoinsReceivedEventListener { wallet, tx, prevBalance, newBalance ->
            doAsync { uiThread { updateStats() } }
        }

        App.walletKit!!.wallet().addCoinsSentEventListener { wallet, tx, prevBalance, newBalance ->
            doAsync { uiThread { updateStats() } }
        }
    }

    private fun updateStats() {
        viewState.updateBalance(App.walletKit!!.wallet().balance.toFriendlyString())
        viewState.updateRecycler(App.walletKit!!.wallet().walletTransactions)

    }

    private fun showMessageIfDownloading() {
        viewState.showMessageDownloading()
    }
}
