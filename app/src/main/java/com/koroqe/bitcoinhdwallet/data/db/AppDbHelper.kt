package com.koroqe.bitcoinhdwallet.data.db

import com.koroqe.bitcoinhdwallet.App
import com.koroqe.bitcoinhdwallet.data.db.models.TxModel
import io.realm.Realm
import javax.inject.Inject

/**
 * Created by Koroqe on 12-Dec-17.
 */

class AppDbHelper : DbHelper {

    @Inject lateinit var realm: Realm

    init { App.component?.inject(this) }

//    // region Transactions
//    override fun getAllTxesFromDb(): RealmResults<TxModel> {
//        return realm.where(TxModel::class.java)
//                .equalTo("forAccount", ApiHelper.keyPair?.accountId)
//                .findAllSorted("date", Sort.DESCENDING)
//    }

    override fun saveTxToDb(tx: TxModel) {
        realm.executeTransaction { realm.copyToRealmOrUpdate(tx) }
    }

    override fun deleteTxFromDb(tx: TxModel) {
        realm.executeTransaction {
            realm.where(TxModel::class.java).equalTo("id", tx.id).findAll().deleteAllFromRealm()
        }
    }

    override fun deleteAllTxesFromDb() {
        realm.executeTransaction {
            realm.where(TxModel::class.java).findAll().deleteAllFromRealm()
        }
    }

    override fun saveTxesToDb(txes: List<TxModel>) {
        realm.executeTransaction { realm.copyToRealmOrUpdate(txes) }
    }

//    override fun getNewTxesFrom(txes: List<TxModel>): List<TxModel> {
//        val allTxes = getAllTxesFromDb()
//        val newTxes: MutableList<TxModel> = ArrayList()
//        txes.filterNotTo(newTxes) { tx -> allTxes.any { it.id == tx.id } }
//        return newTxes
//    }
    // endregion Transactions

    override fun clearRealm() = realm.deleteAll()
}