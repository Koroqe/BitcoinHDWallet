package com.koroqe.bitcoinhdwallet.di.component

/**
 * Created by Koroqe on 19-Dec-17.
 */

interface HasComponent<C> {

    fun getComponent() : C
}
