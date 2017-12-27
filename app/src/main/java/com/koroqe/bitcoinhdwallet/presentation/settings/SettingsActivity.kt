package com.koroqe.bitcoinhdwallet.presentation.settings

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.databinding.ActivitySettingsBinding
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.RestoreFragment
import com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed.ShowSeedFragment

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
}
