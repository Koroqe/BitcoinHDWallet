package com.koroqe.bitcoinhdwallet.data.prefs

import java.math.BigDecimal

/**
 * Created by Koroqe on 12-Dec-17.
 */

interface SharedPrefsHelper {

    fun saveSeedWithEncryption(seed: String, defaultEncryptionKey: Boolean)
    fun getDecryptedSeed(defaultEncryptionKey: Boolean): String
    fun getEncryptedSeed(): String
    fun removeSavedSeed()

    // region Account
    fun getBalance(): BigDecimal
    fun getBalanceFormatted(): String
    fun setBalance(balance: BigDecimal)
    fun getCurrentCurrencyName(): String
    fun setCurrentCurrencyName(currencyName: String)


    fun isAccountExisted() : Boolean
    fun setAccountExisted(boolean: Boolean)
    // endregion Account
    fun setWalletFirstLaunch(boolean: Boolean)

    fun isFirstLaunch(): Boolean
}