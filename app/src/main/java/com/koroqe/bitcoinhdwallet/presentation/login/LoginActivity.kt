package com.koroqe.bitcoinhdwallet.presentation.login

import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.R
import com.koroqe.bitcoinhdwallet.base.BaseActivity
import com.koroqe.bitcoinhdwallet.data.Repository
import com.koroqe.bitcoinhdwallet.databinding.ActivityLoginBinding
import com.koroqe.bitcoinhdwallet.event.EventOpenMainScreen
import com.koroqe.bitcoinhdwallet.event.EventOpenRestoreFragment
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.login.LoginFragment
import com.koroqe.bitcoinhdwallet.presentation.login.fragments.restore.RestoreFragment
import com.koroqe.bitcoinhdwallet.presentation.main.MainActivity
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


/**
 * Created by Koroqe on 14-Dec-17.
 *
 */

class LoginActivity : BaseActivity() {

    @Inject lateinit var repository: Repository

    lateinit var binding: ActivityLoginBinding

    init {
        App.component?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        checkPermissions()
        addLoginFragment()
    }

//    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View {
//        return super.onCreateView(name, context, attrs)
//    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkAccountExistance()
                } else {
                    checkPermissions()
                }
                return
            }
        }
    }

    private fun checkAccountExistance() {

        if (repository.isAccountExisted()) openMainScreen()
    }

    private fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun addLoginFragment() {
        addFragmentWithBackStack(binding.loginContainer.id, LoginFragment.newInstance(), LoginFragment.TAG)
    }

    override fun onResume() {
        registerEventBus()
        super.onResume()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenRestoreFragment) {
        addFragmentWithBackStack(R.id.loginContainer, RestoreFragment.newInstance(), RestoreFragment.TAG)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventOpenMainScreen) {
        openMainScreen()
    }
}