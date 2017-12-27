package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentRestoreBinding
import com.koroqe.bitcoinhdwallet.event.EventOpenMainScreen

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class RestoreFragment : BaseFragment(), RestoreContract.View {

    lateinit var binding: FragmentRestoreBinding

    @InjectPresenter
    lateinit var presenter : RestorePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restore, container, false)
        return binding.root
    }

    override fun restore() {

    }

    override fun getCurrentTime()  {
        System.currentTimeMillis()
    }

    override fun showErrorUnknownSeed() {

    }

    override fun showProgressBarDownload() {
        //set download listener
        binding.frameProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBarDownload() {
        binding.frameProgressBar.visibility = View.GONE
    }

    override fun setDownloadProgress(progress : Double) {
        binding.restoreDownloadInfo.text = (progress.toString() + "%")
        binding.restoreProgressBar.progress = progress.toInt()
    }

    override fun goToMainScreen() {
        eventBus.post(EventOpenMainScreen())
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
    }

    companion object {

        val TAG : String = "RestoreFragment"

        fun newInstance(): RestoreFragment {
            val fragment = RestoreFragment()
//        val args = Bundle()
//        fragment.setArguments(args)
            return fragment
        }
    }
}
