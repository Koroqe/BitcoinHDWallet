package com.koroqe.bitcoinhdwallet.presentation.login

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.databinding.ActivityLoginBinding
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.RestoreFragment
import com.koroqe.bitcoinhdwallet.presentation.main.MainActivity
import javax.inject.Inject



/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginPresenter

//    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mBinding: ActivityLoginBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.listener = presenter

    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View {
        presenter.attachView(this)
        checkPermissions()
        return super.onCreateView(name, context, attrs)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_STORAGE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.checkAccountExistance()
                } else {
                    checkPermissions()
                }
                return
            }
        }
    }

    override fun openMainScreen() {

        val intent = Intent(this, MainActivity::class.java )
        startActivity(intent)
    }

    override fun openRestoreScreen() {

        addFragment(R.id.loginContainer, RestoreFragment.newInstance(), RestoreFragment.TAG)
    }

//    override fun onActivityInject() {
//        DaggerAc.builder().appComponent(getAppcomponent())
//                .homeActivityModule(HomeActivityModule())
//                .build()
//                .inject(this)
//
//        presenter.attachView(this)
//    }

}