package com.koroqe.bitcoinhdwallet.data.db

import com.koroqe.bitcoinhdwallet.data.db.models.TxModel

/**
 * Created by Koroqe on 12-Dec-17.
 */

interface DbHelper {

    // region Transactions
//    fun getAllTxesFromDb(): List<TxModel>

    fun saveTxToDb(tx: TxModel)

    fun deleteTxFromDb(tx: TxModel)

    fun deleteAllTxesFromDb()

    fun saveTxesToDb(txes: List<TxModel>)

//    fun getNewTxesFrom(txes: List<TxModel>): List<TxModel>
    // endregion Transactions

    fun clearRealm()
}