package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentRestoreBinding

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

    override val toolbar: Toolbar = binding.toolbarRestore

    override fun restore() {

    }

    override fun getCurrentTime()  {
        System.currentTimeMillis()
    }

    override fun showErrorUnknownSeed() {

    }

    override fun showProgressBarDownload() {
//        binding.fra
    }

    override fun hideProgressBarDownload() {

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
