package com.koroqe.bitcoinhdwallet.presentation.main.fragments.send

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.databinding.FragmentRestoreBinding
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.SendContract

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class SendFragment : Fragment(), SendContract.View {

    lateinit var binding : FragmentRestoreBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restore, container, false)
        var myView : View = binding.root
        return myView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun openQRscanner() {

    }

    override fun updateBalance() {

    }

}
