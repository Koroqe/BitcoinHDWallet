package com.koroqe.bitcoinhdwallet.presentation.main.fragments.main

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.koroqe.bitcoinhdwallet.databinding.TxItemBinding
import org.bitcoinj.core.Transaction
import org.bitcoinj.wallet.WalletTransaction
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by Koroqe on 15-Dec-17.
 *
 */

class MainRecyclerAdapter(
        context: Context,
        onTxItemClickListener: TxItemClickListener,
        walletTransactions: Iterable<WalletTransaction>
)
    : RecyclerView.Adapter<MainRecyclerAdapter.TxViewHolder>() {

    private var txes: ArrayList<WalletTransaction> = ArrayList()
    private var context: Context by Delegates.notNull<Context>()
    private var clickListener: TxItemClickListener by Delegates.notNull<TxItemClickListener>()

    init {
        this.context = context
        this.clickListener = onTxItemClickListener
        for (e in walletTransactions) {
            txes.add(e)
        }
    }

    override fun getItemCount(): Int  = txes.size

    override fun onBindViewHolder(holder: TxViewHolder, position: Int) {

        val transaction : Transaction = txes.get(position).transaction
        holder.date?.text = transaction.updateTime.toString()

//        App.walletKit!!.wallet().getTransactions().ge
//        if (transaction.getWalletOutputs())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TxViewHolder {

        val inflater = LayoutInflater.from(parent?.context)
        val binding = TxItemBinding.inflate(inflater, parent, false)
        return TxViewHolder(binding.root)
    }

    class TxViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding : TxItemBinding
        val date: TextView?
        var amount: TextView? = null
        val address: TextView?
        val container: ConstraintLayout?

        init {
            binding = DataBindingUtil.bind(v)
            amount = binding.tvAmount
            address = binding.tvAddress
            date = binding.tvDate
            container = binding.container
        }
    }
}