package com.koroqe.bitcoinhdwallet.wallet

import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.kits.WalletAppKit
import java.io.File

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class WalletKit constructor(
        params: NetworkParameters,
        directory: File,
        filePrefix: String)
    : WalletAppKit(params, directory, filePrefix) {

    var setupCompletedListener : SetupCompletedListener? = null

    override fun onSetupCompleted() {
        super.onSetupCompleted()
        setupCompletedListener?.onSetupCompleted()
    }

    override fun startUp() {
        super.startUp()
    }

    abstract class SetupCompletedListener {

        abstract fun onSetupCompleted()
    }
}