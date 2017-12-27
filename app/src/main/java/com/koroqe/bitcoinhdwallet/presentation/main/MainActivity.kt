package com.koroqe.bitcoinhdwallet.presentation.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.databinding.ActivityMainBinding
import com.koroqe.bitcoinhdwallet.event.*
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.ReceiveFragment
import com.koroqe.bitcoinhdwallet.presentation.main.fragments.main.MainFragment
import com.koroqe.bitcoinhdwallet.presentation.main.fragments.send.SendFragment
import com.koroqe.bitcoinhdwallet.presentation.qrcode.QrActivity
import com.koroqe.bitcoinhdwallet.presentation.settings.SettingsActivity
import com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed.ShowSeedFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        addMainFragment()
    }

    private fun addMainFragment() {
        addFragment(binding.mainContainer.id, MainFragment.newInstance(), MainFragment.TAG)
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenSendFragmentWithInvoice) {
        addFragmentWithBackStack(binding.mainContainer.id, SendFragment.newInstance(event.qrContent), SendFragment.TAG)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenSendFragment) {
        addFragmentWithBackStack(binding.mainContainer.id, SendFragment.newInstance(""), SendFragment.TAG)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenReceiveFragment) {
        addFragmentWithBackStack(binding.mainContainer.id, ReceiveFragment.newInstance(), ReceiveFragment.TAG)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenSettingsActivity) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenQRCodeScanner) {
        startActivity(Intent(this, QrActivity::class.java))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenShowSeedFragment) {
        addFragmentWithBackStack(binding.mainContainer.id, ShowSeedFragment.newInstance(), ShowSeedFragment.TAG)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventRemoveSeedFragment) {
        removeFragment(SendFragment.TAG)
    }
}
