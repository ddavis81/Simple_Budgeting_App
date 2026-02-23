package com.example.budgetapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.data.Expense
import com.example.budgetapp.data.ExpenseDatabase
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = ExpenseDatabase.getDatabase(application).expenseDao()
    val expenses = dao.getAllExpenses()

    fun addExpense(title: String, amount: Double) {
        viewModelScope.launch {
            dao.insert(Expense(title = title, amount = amount))
        }
    }
}
