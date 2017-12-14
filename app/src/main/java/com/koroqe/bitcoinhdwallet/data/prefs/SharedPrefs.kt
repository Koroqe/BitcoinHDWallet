package com.koroqe.bitcoinhdwallet.data.prefs

import android.content.SharedPreferences
import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.utils.Encrypter
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by Koroqe on 12-Dec-17.
 */

class SharedPrefs : SharedPrefsHelper {

    // region Keys
    val IS_PIN_CODE_ENABLED = "is_pin_code_enabled"
    val IS_PIN_CODE_EXISTS = "is_pin_code_exists"
    val BALANCE = "balance"
    val CURRENT_CURRENCY = "current_currency"
    val CURRENT_USER_PHONE_NUMBER = "current_user_phone_number"
    val CURRENT_BALANCE_ID = "current_balance_id"
    val ENCRYPTED_SEED = "encrypted_seed"

//    // No not change! This key is provided with pin code lib
//    // Contains hash of pin code
//    val PASSWORD_PREFERENCE_KEY = "PASSCODE"
//    // endregion Keys

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    init {
        App.component?.inject(this)
    }

    override fun getDecryptedSeed(defaultEncryptionKey: Boolean): String  {
        val key = Encrypter.DEFAULT_CIPHER_KEY
        return Encrypter.decryptString(getStringByKey(ENCRYPTED_SEED), key)
    }

    override fun getEncryptedSeed() = getStringByKey(ENCRYPTED_SEED)
    override fun removeSavedSeed() = remove(ENCRYPTED_SEED)

    override fun saveSeedWithEncryption(seed: String, defaultEncryptionKey: Boolean) {
        val encryptionKey = Encrypter.DEFAULT_CIPHER_KEY
        Timber.e("encryptionKey = " + encryptionKey)
        saveString(ENCRYPTED_SEED, Encrypter.encryptString(seed, encryptionKey))
    }

    // endregion Pin code

    // region Account
    override fun getBalance() = BigDecimal(getStringByKey(BALANCE))
    override fun getBalanceFormatted(): String {
        val balance = getStringByKey(BALANCE)
        val indexOfDot = balance.indexOf(".")
        return balance.substring(0, indexOfDot + 3) + " " + getCurrentCurrencyName()
    }

    override fun setBalance(balance: BigDecimal) = saveString(BALANCE, balance.toPlainString())
    override fun getCurrentCurrencyName() = getStringByKey(CURRENT_CURRENCY)
    override fun setCurrentCurrencyName(currencyName: String) = saveString(CURRENT_CURRENCY, currencyName)
    override fun getCurrentUserPhoneNumber() = getStringByKey(CURRENT_USER_PHONE_NUMBER)
    override fun setCurrentUserPhoneNumber(phone: String) = saveString(CURRENT_USER_PHONE_NUMBER, phone)
    override fun setCurrentBalanceId(balanceId: String) = saveString(CURRENT_BALANCE_ID, balanceId)
    override fun getCurrentBalanceId() = getStringByKey(CURRENT_BALANCE_ID)
    // endregion Account

    // region General helping methods
    fun saveString(key: String, value: String) = sharedPrefs.edit().putString(key, value).apply()
    fun remove(key: String) = sharedPrefs.edit().remove(key).apply()
    fun getStringByKey(key: String): String = sharedPrefs.getString(key, "")
    fun saveBoolean(key: String, value: Boolean) = sharedPrefs.edit().putBoolean(key, value).apply()
    fun getBooleanByKey(key: String): Boolean = sharedPrefs.getBoolean(key, false)
    // endregion General helping methods
}