package com.koroqe.bitcoinhdwallet.utils

import android.app.Fragment
import android.content.Context
import com.google.zxing.integration.android.IntentIntegrator
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.presentation.qrcode.QrActivity

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

object QrHelper {

    fun openQrScanner(context: Context, fragment: Fragment) {
        IntentIntegrator.forFragment(fragment)
                .setOrientationLocked(false)
                .setBeepEnabled(false)
                .setCaptureActivity(QrActivity::class.java)
                .setPrompt(context.getString(R.string.scan_qr_text))
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                .initiateScan()
    }

}