package com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.databinding.FragmentReceiveBinding
import com.koroqe.bitcoinhdwallet.presentation.main.fragments.receive.ReceivePresenter
import net.glxn.qrgen.android.QRCode


/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class ReceiveFragment : MvpFragment(), ReceiveContract.View {

    lateinit var binding: FragmentReceiveBinding

    @InjectPresenter
    lateinit var presenter : ReceivePresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receive, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setupData()
        initQrCode()
        initEditTextAmount()
    }

    override fun copyToClipboard(address: String) {

        val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("address", address)
        clipboard.primaryClip = clip
    }

    override fun setReceiveAddress(receiveAddress: String?) {

        binding.tvAddress.text = receiveAddress
    }

    override fun showMessageAdressCopied() {

        Toast.makeText(activity, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun initQrCode() {

        val myBitmap = QRCode.from(buildReceiveInvoice()).withSize(300, 300).bitmap()
        binding.ivQRCode.setImageBitmap(myBitmap)
    }

    private fun initEditTextAmount() {

        binding.etAmount.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged (amount: Editable?) {
                val myBitmap = QRCode.from(buildReceiveInvoice(amount.toString()))
                        .withSize(300, 300).bitmap()
                binding.ivQRCode.setImageBitmap(myBitmap)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun buildReceiveInvoice(amount: String = "") =

            if (amount.isEmpty())
                "balanceID:${presenter.getReceiveAdress()}"
            else
                "balanceID:${presenter.getReceiveAdress()} amount:$amount"

    companion object {

        val TAG: String = "ReceiveFragment"

        fun newInstance(): ReceiveFragment {
            val fragment = ReceiveFragment()
//        val args = Bundle()
//        fragment.setArguments(args)
            return fragment
        }
    }
}
