package com.koroqe.bitcoinhdwallet.presentation.login.fragments.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseFragment
import com.koroqe.bitcoinhdwallet.databinding.FragmentLoginBinding
import com.koroqe.bitcoinhdwallet.event.EventOpenMainScreen
import com.koroqe.bitcoinhdwallet.event.EventOpenRestoreFragment

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

class LoginFragment : BaseFragment(), LoginContract.View {

    lateinit var binding: FragmentLoginBinding

    @InjectPresenter
    lateinit var presenter : LoginPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.listener = presenter
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun openMainScreen() {

        eventBus.post(EventOpenMainScreen())
    }

    override fun openRestoreScreen() {

        eventBus.post(EventOpenRestoreFragment())
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
        presenter.setupData()
    }

    override fun showProgressBar() {

        binding.progressBarLogin.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {

        binding.progressBarLogin.visibility = View.GONE
    }

    companion object {

        val TAG : String = "LoginFragment"

        fun newInstance(): LoginFragment {
            val fragment = LoginFragment()
//        val args = Bundle()
//        fragment.setArguments(args)
            return fragment
        }
    }
}
