package com.koroqe.bitcoinhdwallet.presentation.settings.fragments.showseed

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentShowSeedBinding

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class ShowSeedFragment : BaseFragment(), ShowSeedContract.View {

    lateinit var binding: FragmentShowSeedBinding

    @InjectPresenter
    lateinit var presenter : ShowSeedPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_seed, container, false)
        binding.listener = presenter
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setupData()
    }

    override fun onResume() {

        registerEventBus()
        super.onResume()
    }

    override fun showCurrentSeed(keyChainSeed: String) {

        binding.textSeed.setText(keyChainSeed)
    }

    override fun onClickOk() {

        activity.onBackPressed()
//        activity.fragmentManager.popBackStackImmediate()
//        eventBus.post(EventRemoveSeedFragment())
    }

    companion object {

        val TAG : String = "ShowSeedFragment"

        fun newInstance(): ShowSeedFragment {
            val fragment = ShowSeedFragment()
//        val args = Bundle()
//        fragment.setArguments(args)
            return fragment
        }
    }
}
