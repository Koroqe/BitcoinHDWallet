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
    // endregion Pin code

    // region Account
    fun getBalance(): BigDecimal
    fun getBalanceFormatted(): String
    fun setBalance(balance: BigDecimal)
    fun getCurrentCurrencyName(): String
    fun setCurrentCurrencyName(currencyName: String)
    fun setCurrentUserPhoneNumber(phone: String)
    fun getCurrentUserPhoneNumber(): String
    fun setCurrentBalanceId(balanceId: String)
    fun getCurrentBalanceId(): String




    fun isAccountExisted() : Boolean


    // endregion Account
}