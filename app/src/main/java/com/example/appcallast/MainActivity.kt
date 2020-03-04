package com.example.appcallast


import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    // TextView used to display the input and output
    lateinit var txtInput: TextView

    // Represent whether the lastly pressed key is numeric or not
    private var lastNumeric: Boolean = false

    // Represent that current state is in error or not
    var stateError: Boolean = true

    // If true, do not allow to add another DOT
    var lastDot: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.txtInput = findViewById(R.id.txtInput)
    }

    //them chu so cho view
    fun onDigit(view: View) {
        if (stateError) {
            txtInput.text = (view as Button).text
            stateError = false
        } else {
            // If not, already there is a valid expression so append to it
            txtInput.append((view as Button).text)
        }
        //flag Lastnumeric
        lastNumeric = true
    }

    //them "." cho view
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            txtInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    //them toan tu +,-,*,/ cho view
    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            txtInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }


    //xoa sach view, reset flag
    fun onClear(view: View) {
        this.txtInput.text = ""
        lastNumeric = false
        stateError = true
        lastDot = false
    }

    //calculate
    fun onEqual(view: View) {
       //tan cung phai la mot chu so
        if (lastNumeric && !stateError) {
            // Read the expression
            val txt = txtInput.text.toString()

            // Create an Expression (A class from exp4j library)
            val expression = ExpressionBuilder(txt).build()
            try {
                // Calculate the result and display
                val result = expression.evaluate()
                txtInput.text = result.toString()
                lastDot = true // Result contains a dot
            }
            catch (ex: ArithmeticException) {
                // Display an error message
                txtInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }

}