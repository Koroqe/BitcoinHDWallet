package com.koroqe.bitcoinhdwallet.data

import com.koroqe.bitcoinhdwallet.data.db.AppDbHelper
import com.koroqe.bitcoinhdwallet.data.db.DbHelper
import com.koroqe.bitcoinhdwallet.data.db.models.TxModel
import com.koroqe.bitcoinhdwallet.data.prefs.SharedPrefs
import com.koroqe.bitcoinhdwallet.data.prefs.SharedPrefsHelper
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Koroqe on 12-Dec-17.
 */

@Singleton
class Repository @Inject constructor(var sharedPrefs: SharedPrefs,
                                     var appDbHelper: AppDbHelper)
    : SharedPrefsHelper, DbHelper {


    // region DB
//    override fun getAllTxesFromDb() = appDbHelper.getAllTxesFromDb()

    override fun saveTxToDb(tx: TxModel) = appDbHelper.saveTxToDb(tx)
    override fun deleteTxFromDb(tx: TxModel) = appDbHelper.deleteTxFromDb(tx)
    override fun deleteAllTxesFromDb() = appDbHelper.deleteAllTxesFromDb()
    override fun saveTxesToDb(txes: List<TxModel>) = appDbHelper.saveTxesToDb(txes)

//    override fun getNewTxesFrom(txes: List<TxModel>) = appDbHelper.getNewTxesFrom(txes)
    override fun clearRealm() = appDbHelper.clearRealm()
    // endregion Db

    // region SharedPrefs

    override fun getBalanceFormatted() = sharedPrefs.getBalanceFormatted()
    override fun getBalance() = sharedPrefs.getBalance()
    override fun setBalance(balance: BigDecimal) = sharedPrefs.setBalance(balance)
    override fun getCurrentCurrencyName() = sharedPrefs.getCurrentCurrencyName()
    override fun setCurrentCurrencyName(currencyName: String) = sharedPrefs.setCurrentCurrencyName(currencyName)
    override fun saveSeedWithEncryption(seed: String, defaultEncryptionKey: Boolean)
            = sharedPrefs.saveSeedWithEncryption(seed, defaultEncryptionKey)
    override fun getDecryptedSeed(defaultEncryptionKey: Boolean) = sharedPrefs.getDecryptedSeed(defaultEncryptionKey)
    override fun getEncryptedSeed() = sharedPrefs.getEncryptedSeed()
    override fun removeSavedSeed() = sharedPrefs.removeSavedSeed()

    override fun isAccountExisted() = sharedPrefs.isAccountExisted()
    override fun setAccountExisted(boolean: Boolean) = sharedPrefs.setAccountExisted(boolean)
    override fun setWalletFirstLaunch(boolean: Boolean) = sharedPrefs.setWalletFirstLaunch(boolean)
    override fun isFirstLaunch(): Boolean = sharedPrefs.isFirstLaunch()

    // endregion SharedPrefs

//    override fun downloadAllTxes() = apiHelper.downloadAllTxes()

//    override fun downloadTxes(cursor: String?, limit: Int) = apiHelper.downloadTxes(cursor, limit)
//
//    override fun getAccount(accountId: String) = apiHelper.getAccount(accountId)
//    override fun submitTransaction(paymentOperation: PaymentOperation) = apiHelper.submitTransaction(paymentOperation)

//    override fun createWallet(phoneNumber: String, password: String,
//                              account: KeyPair, tfaAccount: KeyPair?)
//            = apiHelper.createWallet(phoneNumber, password, account, tfaAccount)

}