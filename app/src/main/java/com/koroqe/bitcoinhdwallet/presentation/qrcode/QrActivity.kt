package com.koroqe.bitcoinhdwallet.presentation.qrcode

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.KeyEvent
import com.journeyapps.barcodescanner.CaptureManager
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.databinding.ActivityQrBinding

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class QrActivity : BaseActivity(), QrContract.View {

    lateinit var binding : ActivityQrBinding
    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr)
        capture = CaptureManager(this, binding.barcodeScanner)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.decode()
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return binding.barcodeScanner.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

}
