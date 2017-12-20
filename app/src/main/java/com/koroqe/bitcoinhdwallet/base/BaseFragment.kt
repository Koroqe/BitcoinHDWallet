package com.koroqe.bitcoinhdwallet.base

import android.content.Context
import android.support.v7.widget.Toolbar
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpFragment
import com.koroqe.bitcoinhdwallet.event.BaseEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Koroqe on 13-Dec-17.
 *
 */

abstract class BaseFragment : MvpFragment() {

    protected var mNeedEventBusRegister = false

    val eventBus: EventBus
        get() = EventBus.getDefault()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: BaseEvent) {
    }

    override fun onPause() {
        super.onPause()
        if (mNeedEventBusRegister) EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        if (mNeedEventBusRegister) EventBus.getDefault().register(this)
    }

    abstract val toolbar: Toolbar

    //should invoke in onResume before calling parent's super method in child fragment
    protected fun registerEventBus() {
        mNeedEventBusRegister = true
    }

    protected fun hideSoftKeyboard() {

        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }
}