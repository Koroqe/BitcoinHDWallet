package com.koroqe.bitcoinhdwallet.base

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.koroqe.bitcoinhdwallet.event.BaseEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */
open class BaseActivity : MvpAppCompatActivity() {

    protected var mNeedEventBusRegister = false

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: BaseEvent) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        if (mNeedEventBusRegister) EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        if (mNeedEventBusRegister) EventBus.getDefault().register(this)
    }

    protected fun registerEventBus() { //no need to unregister manually
        mNeedEventBusRegister = true
    }

    fun addFragment(viewGroupId: Int, fragment: Fragment, tag: String) {
        fragmentManager
                .beginTransaction()
                .add(viewGroupId, fragment, tag)
                .commitAllowingStateLoss()
    }

    fun addFragmentWithBackStack(viewGroupId: Int, fragment: Fragment, tag: String) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(tag)
                .add(viewGroupId, fragment, tag)
                .commitAllowingStateLoss()
    }

    fun replaceFragmentWithBackStack(viewGroupId: Int, fragment: Fragment, tag: String) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(tag)
                .replace(viewGroupId, fragment, tag)
                .commitAllowingStateLoss()
    }

    fun resolveToolbar(fragment: BaseFragment, title: String) {

        val toolbar = fragment.toolbar
        toolbar.visibility = View.VISIBLE
        setSupportActionBar(toolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = title

//        if (fragment.toolbarNavigationActive()) {
//            toolbar.setNavigationOnClickListener({ onBackPressed() })
//        }
    }

    fun getEventBus(): EventBus {
        return EventBus.getDefault()
    }

    fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        PERMISSIONS_REQUEST_STORAGE);
//            }
        }
    }

    protected fun hideSoftKeyboard() {

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        try {
            imm!!.hideSoftInputFromWindow(getCurrentFocus()!!.getWindowToken(), 0)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    companion object {

        var PERMISSIONS_REQUEST_STORAGE = 10101

        fun newIntent(context: Context, activity : BaseActivity): Intent {
            val intent = Intent(context, activity::class.java)
            return intent
        }
    }
}