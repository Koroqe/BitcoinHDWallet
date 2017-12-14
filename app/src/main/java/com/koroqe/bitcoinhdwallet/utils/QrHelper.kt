package com.distributedlab.kuna.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.distributedlab.kuna.R
import com.distributedlab.kuna.ScanQrActivity
import com.google.zxing.integration.android.IntentIntegrator

/**
 * Created by danielyakovlev on 6/20/17.
 */

object QrHelper {

    fun openQrScanner(context: Context, fragment: Fragment, disablePinCodeFunction: () -> Unit) {
        disablePinCodeFunction()
        IntentIntegrator.forSupportFragment(fragment)
                .setOrientationLocked(false)
                .setBeepEnabled(false)
                .setCaptureActivity(ScanQrActivity::class.java)
                .setPrompt(context.getString(R.string.scan_qr_description))
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                .initiateScan()
    }

    fun processQrContent(content: String, activity: AppCompatActivity) {
        Navigator.openSendFragmentWithInvoice(content, activity)
    }
}