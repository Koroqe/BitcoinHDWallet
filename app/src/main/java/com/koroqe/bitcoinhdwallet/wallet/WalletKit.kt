package com.koroqe.bitcoinhdwallet.wallet

import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.kits.WalletAppKit
import java.io.File

/**
 * Created by Koroqe on 19-Dec-17.
 */

class WalletKit constructor(
        params: NetworkParameters,
        directory: File,
        filePrefix: String)
    : WalletAppKit(params, directory, filePrefix) {

    override fun onSetupCompleted() {
        super.onSetupCompleted()
    }
}