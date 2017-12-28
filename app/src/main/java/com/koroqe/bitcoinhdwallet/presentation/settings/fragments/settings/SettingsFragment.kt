package com.koroqe.bitcoinhdwallet.presentation.settings.fragments.settings

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentSettingsBinding
import com.koroqe.bitcoinhdwallet.event.EventOpenRestoreFragment
import com.koroqe.bitcoinhdwallet.event.EventOpenShowSeedFragment

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class SettingsFragment : BaseFragment(), SettingsContract.View {

    lateinit var binding: FragmentSettingsBinding

    @InjectPresenter
    lateinit var presenter : SettingsPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.listener = presenter
        return binding.root
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
    }

    override fun openShowSeedScreen() {
        eventBus.post(EventOpenShowSeedFragment())
    }

    override fun openRestoreWalletScreen() {
        eventBus.post(EventOpenRestoreFragment())
    }

    companion object {

        val TAG : String = "SettingsFragment"

        fun newInstance(): SettingsFragment {
            val fragment = SettingsFragment()
            return fragment
        }
    }
}
