package com.koroqe.bitcoinhdwallet.utils

import com.google.zxing.integration.android.IntentIntegrator
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.presentation.qrcode.QrActivity

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

object QrHelper {

    fun openQrScanner(activity: BaseActivity) {
        IntentIntegrator(activity)
                .setOrientationLocked(false)
                .setBeepEnabled(false)
                .setCaptureActivity(QrActivity::class.java)
                .setPrompt(activity.getString(R.string.scan_qr_text))
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                .initiateScan()
    }
}