package com.example.personalexpensetracker.repository

import com.example.personalexpensetracker.data.AppDatabase
import com.example.personalexpensetracker.model.Transaction
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val db: AppDatabase
) {

    suspend fun insert(transaction: Transaction) =
        db.getTransactionDao().insertTransaction(transaction)

    suspend fun update(transaction: Transaction) =
        db.getTransactionDao().updateTransaction(transaction)

    suspend fun delete(transaction: Transaction) =
        db.getTransactionDao().deleteTransaction(transaction)

    fun getAllTransactions() = db.getTransactionDao().getAllTransactions()

    fun getAllSingleTransaction(transactionType:String) =
        if(transactionType == "Overall"){
            getAllTransactions()
        }else{
            db.getTransactionDao().getAllSingleTransaction(transactionType)
        }

    fun getByID(id:Int) = db.getTransactionDao().getTransactionByID(id)
}