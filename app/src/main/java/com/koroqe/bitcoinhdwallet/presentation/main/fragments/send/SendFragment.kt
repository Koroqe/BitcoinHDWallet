package com.koroqe.bitcoinhdwallet.presentation.main.fragments.send

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentSendBinding
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.SendContract
import com.koroqe.bitcoinhdwallet.presentation.main.MainActivity
import com.koroqe.bitcoinhdwallet.utils.QrHelper

/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class SendFragment : BaseFragment(), SendContract.View {

    @InjectPresenter
    lateinit var presenter : SendPresenter

    lateinit var binding : FragmentSendBinding

    private lateinit var qrContent: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_send, container, false)
        binding.listener = presenter
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (qrContent.isNotEmpty()) {
            fillInvoiceData()
        }
    }

    override fun openQRscanner() {
        QrHelper.openQrScanner(activity as MainActivity)
    }

    override fun showError(message: String?) {
        Toast.makeText(activity.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessageSelectRecepient() {
        Toast.makeText(activity.applicationContext, getString(R.string.select_recepient), Toast.LENGTH_SHORT).show()
    }

    override fun showMessageEnterAmount() {
        Toast.makeText(activity.applicationContext, getString(R.string.enter_amount), Toast.LENGTH_SHORT).show()
    }

    override fun showMessageNotEnoughFunds() {
        Toast.makeText(activity.applicationContext, getString(R.string.not_enough_funds), Toast.LENGTH_SHORT).show()
    }

    override fun resetAmount() {
        binding.etAmount.setText("")
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
    }

    private fun fillInvoiceData() {

        binding.tvAddress.setText(qrContent)
    }

    companion object {

        val TAG : String = "SendFragment"

        fun newInstance(content : String): SendFragment {
            val fragment = SendFragment()
            fragment.qrContent = content
            return fragment
        }
    }
}
