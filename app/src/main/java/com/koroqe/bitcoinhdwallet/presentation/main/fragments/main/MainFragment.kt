package com.koroqe.bitcoinhdwallet.presentation.main.fragments.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentMainBinding
import com.koroqe.bitcoinhdwallet.event.*
import org.bitcoinj.wallet.WalletTransaction


/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class MainFragment : BaseFragment(), MainContract.View {

    lateinit var binding: FragmentMainBinding

    @InjectPresenter
    lateinit var presenter : MainPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.listener = presenter
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setupData()
    }

    override fun updateRecycler(walletTransactions: Iterable<WalletTransaction>) {
        binding.mainRecyclerTransactions.adapter = MainRecyclerAdapter(activity.applicationContext, presenter, walletTransactions)
    }

    override fun openSendScreen() {

        eventBus.post(EventOpenSendFragment())
    }

    override fun openReceiveScreen() {

        eventBus.post(EventOpenReceiveFragment())
    }

    override fun openSettings() {

        eventBus.post(EventOpenSettingsActivity())
    }

    override fun openQRcodeScanner() {

        eventBus.post(EventOpenQRCodeScanner())
    }

    override fun showSeedForBackup() {

        eventBus.post(EventOpenShowSeedFragment())
    }

    override fun hideDownloadProgressBar() {

        binding.downloadProgressBar.visibility = View.GONE
        binding.tvSynchronizing.visibility = View.GONE
    }

    override fun setDownloadProgress(progress: Int) {

        binding.downloadProgressBar.visibility = View.VISIBLE
        binding.tvSynchronizing.visibility = View.VISIBLE
        binding.downloadProgressBar.progress = progress
        binding.tvSynchronizing.text = "Synchronizing... $progress%"
    }

    override fun updateBalance(balance: String) {
    }

    override fun onResume() {

        registerEventBus()
        super.onResume()
    }

    override fun showMessageDownloading() {

        Toast.makeText(
                activity.applicationContext,
                getString(R.string.wait_downloading),
                Toast.LENGTH_SHORT)
                .show()
    }

    companion object {

        val TAG: String = "MainFragment"

        fun newInstance(): MainFragment {
            val fragment = MainFragment()
//        val args = Bundle()
//        fragment.setArguments(args)
            return fragment
        }
    }
}
