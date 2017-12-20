package com.koroqe.bitcoinhdwallet.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.databinding.ActivityMainBinding

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class MainActivity : BaseActivity(), MainContract.View {

    @InjectPresenter
    lateinit var presenter : MainPresenter

//    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun openSendScreen() {

    }

    override fun openReceiveScreen() {

    }

    override fun openSettings() {

    }

    override fun openQRcodeScanner() {

    }
}