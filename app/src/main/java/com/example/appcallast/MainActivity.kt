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

    var isFirstNum : Boolean = true
    var firstNum : String? = null
    var secondNum : String? = null
    var ope : CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.txtInput = findViewById(R.id.txtInput)
    }

    //them chu so cho view
    fun onDigit(view: View) {
        var s : CharSequence? = (view as Button).text
        if (stateError) {
            txtInput.text = s

            stateError = false
            if (isFirstNum) {
                firstNum = s.toString()
            } else {
                secondNum = s.toString()
            }
        } else {
            // If not, already there is a valid expression so append to it
            txtInput.append((view as Button).text)

            if (isFirstNum) {
                firstNum.plus(s)
            } else {
                secondNum.plus(s)
            }
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

            isFirstNum = false
            ope = (view as Button).text.toString()
        }
    }


    //xoa sach view, reset flag
    fun onClear(view: View) {
        this.txtInput.text = ""
        lastNumeric = false
        stateError = true
        lastDot = false

        isFirstNum = true
    }

    //calculate
    fun onEqual(view: View) {
       //tan cung phai la mot chu so
        var a : Long = firstNum!!.toLong()
        var b : Long = secondNum!!.toLong()
        if (lastNumeric && !stateError) {

            when(ope){
                "+" -> { txtInput.text = (firstNum!!.toLong() + secondNum!!.toLong()).toString() }
                "-" -> { txtInput.text = (firstNum!!.toLong() - secondNum!!.toLong()).toString() }
                "*" -> { txtInput.text = (firstNum!!.toLong() * secondNum!!.toLong()).toString() }

                else -> { txtInput.text = (firstNum!!.toLong() / secondNum!!.toLong()).toString() }

            }
//            // Read the expression
//            val txt = txtInput.text.toString()
//
//            // Create an Expression (A class from exp4j library)
//            val expression = ExpressionBuilder(txt).build()
//            try {
//                // Calculate the result and display
//                val result = expression.evaluate()
//                txtInput.text = result.toString()
//                lastDot = true // Result contains a dot
//            }
//            catch (ex: ArithmeticException) {
//                // Display an error message
//                txtInput.text = "Error"
//                stateError = true
//                lastNumeric = false
//            }
        }
    }

}