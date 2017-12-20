package com.koroqe.bitcoinhdwallet.data.db.models

import android.content.Context
import com.koroqe.bitcoinhdwallet.R
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Koroqe on 12-Dec-17.
 */

open class TxModel(@PrimaryKey var id: Long = 0,
                   var amount: String? = "",
                   var assetType: String? = "",
                   var from: String? = "",
                   var to: String? = "",
                   var createdAt: String? = "",
                   var fixedFee: String? = "",
                   var paymentFee: String? = "",
                   var type: String? = "",
                   var isFeeFromSource: Boolean = false,
                   var paymentType: String? = "",
                   var subject: String? = "",
//                   var paymentParticipants: RealmList<PaymentParticipantModel>? = RealmList(),
                   var fromBalance: String? = "",
                   var toBalance: String? = "",
                   var pagingToken: String? = "",
                   var counterpartyPhoneNumber: String? = "",
                   var forAccount: String? = ""
//                   var date: Date? = ApiDateHelper.tryParseDate(createdAt)
) : RealmObject() {


    enum class PaymentState {
        PENDING, SUCCESS, REJECTED, FAILED;

        fun name(context: Context): String {
            return when (this) {
                PENDING -> context.getString(R.string.pending)
                SUCCESS -> context.getString(R.string.success)
                REJECTED -> context.getString(R.string.rejected)
                FAILED -> context.getString(R.string.failed)
                else -> ""
            }
        }

        companion object {
            fun fromCode(code: Int?): PaymentState {
                return when (code) {
                    2 -> SUCCESS
                    3 -> REJECTED
                    4 -> FAILED
                    else -> PENDING
                }
            }
        }
    }

//    val isReceived: Boolean
//        get() {
//            if (ApiHelper.keyPair?.accountId == this.to) {
//                return true
//            }
//            return false
//        }

//    val isSent: Boolean
//        get() = !isReceived
//
//    operator fun List<TxModel>.contains(tx: TxModel): Boolean {
//        return this.filter { it.id == tx.id }.any()
//    }

//    companion object {
//        val TYPE_PAYMENT = "payment"
//        val PAYMENT_TYPE_TRANSFER = "Transfer"
//        val PAYMENT_TYPE_INVOICE = "Invoice"
//        val PAYMENT_TYPE_GIFT = "Gift"
//
//        fun convertToRealmObjects(txRecords: List<PaymentsResponse.Embedded.Record>): List<TxModel> {
//            val txes: MutableList<TxModel> = ArrayList()
//            txRecords.mapTo(txes) { recordToTxModel(it) }
//            return txes
//        }
//
//        fun recordToTxModel(record: PaymentsResponse.Embedded.Record): TxModel {
//            try {
//                return TxModel(amount = record.amount, from = record.from, to = record.to,
//                        createdAt = record.ledgerCloseTime, fixedFee = record.fixedFee,
//                        paymentFee = record.paymentFee, type = record.type,
//                        id = record.id!!.toLong(), isFeeFromSource = record.isFeeFromSource,
//                        paymentType = record.paymentType, subject = record.subject,
//                        paymentParticipants = RealmHelper.getRealmListFromList(
//                                PaymentParticipantModel.convertFromApiModel(record.participants)),
//                        fromBalance = record.fromBalance, toBalance = record.toBalance,
//                        pagingToken = record.pagingToken, forAccount = ApiHelper.keyPair?.accountId)
//            } catch (e: Exception) {
//                Timber.e(e.message)
//            }
//            return TxModel()
//        }
//    }

}

