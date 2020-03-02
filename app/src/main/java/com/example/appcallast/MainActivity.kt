package com.example.appcallast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sothu1 : Int = (firstNum.text.toString().toInt())
        var sothu2 : Int = (secondNum.text.toString().toInt())
        var ketqua : Int = sothu1 + sothu2
        result.text = ketqua.toString()

    }
}
