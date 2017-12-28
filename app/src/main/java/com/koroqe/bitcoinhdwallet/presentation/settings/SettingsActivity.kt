package com.koroqe.bitcoinhdwallet.presentation.settings

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.databinding.ActivitySettingsBinding
import com.koroqe.bitcoinhdwallet.event.EventOpenMainScreen
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.RestoreFragment
import com.koroqe.bitcoinhdwallet.presentation.main.MainActivity
import com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed.ShowSeedFragment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SettingsActivity : BaseActivity(), SettingsContract.View {

    @InjectPresenter
    lateinit var presenter : SettingsPresenter

    lateinit var binding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        binding.listener = presenter
    }

    override fun openShowSeedScreen() {
        addFragmentWithBackStack(R.id.settingsContainer, ShowSeedFragment.newInstance(), ShowSeedFragment.TAG)
    }

    override fun openRestoreWalletScreen() {
        addFragmentWithBackStack(R.id.settingsContainer, RestoreFragment.newInstance(), RestoreFragment.TAG)
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
    }

    private fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenMainScreen) {
        openMainScreen()
        finish()
    }
}
