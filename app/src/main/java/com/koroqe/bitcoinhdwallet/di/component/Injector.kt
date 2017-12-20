package com.koroqe.bitcoinhdwallet.di.component

/**
 * Created by Koroqe on 15-Dec-17.
 *
 */

class Injector {
    fun <C> getComponent(o: Any, component: Class<C>): C? {
        return component.cast((o as HasComponent<*>).component)
    }
}