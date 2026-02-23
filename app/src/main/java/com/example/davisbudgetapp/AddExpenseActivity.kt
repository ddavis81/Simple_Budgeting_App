package com.example.budgetapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetapp.viewmodel.ExpenseViewModel

class AddExpenseActivity : AppCompatActivity() {

    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val title = findViewById<EditText>(R.id.titleInput)
        val amount = findViewById<EditText>(R.id.amountInput)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {
            val titleText = title.text.toString()
            val amountValue = amount.text.toString().toDoubleOrNull() ?: 0.0
            viewModel.addExpense(titleText, amountValue)
            finish()
        }
    }
}
