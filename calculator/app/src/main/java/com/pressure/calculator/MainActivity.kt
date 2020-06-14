package com.pressure.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    private  lateinit var number : EditText
    private  val tvresult by lazy { findViewById<TextView>(R.id.tvresult) }
    private  val tvoperation by lazy { findViewById<TextView>(R.id.tvoperation) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         number = findViewById(R.id.etNumber)

        val listener = View.OnClickListener { view ->
            val button = view as Button
            number.append(button.text)
        }

        val oplistener  = View.OnClickListener {
            view ->  val button = view as Button
            if(tvoperation.text.isEmpty()) {
                tvoperation.text = button.text
                tvresult.text = (number.text)
                clearText()
            }
            else
            {
                if(number.text.isNotEmpty())
                calculateResult()

                tvoperation.text = button.text
            }

        }
        bt00.setOnClickListener(listener)
        btzero.setOnClickListener(listener)
        bt1.setOnClickListener(listener)
        bt2.setOnClickListener(listener)
        bt3.setOnClickListener(listener)
        bt4.setOnClickListener(listener)
        bt5.setOnClickListener(listener)
        bt6.setOnClickListener(listener)
        bt7.setOnClickListener(listener)
        bt8.setOnClickListener(listener)
        bt9.setOnClickListener(listener)
        btdot.setOnClickListener(listener)

        btadd.setOnClickListener(oplistener)
        btsub.setOnClickListener(oplistener)
        btmul.setOnClickListener(oplistener)
        btdiv.setOnClickListener(oplistener)
        btpercentile.setOnClickListener(oplistener)
        btmod.setOnClickListener(oplistener)

        btneg.setOnClickListener {
            if(number.text.isNotEmpty())
            {
                val temp = number.text.toString().toDouble() * -1
                number.setText(temp.toString())
            }


        }


        btclear.setOnClickListener( View.OnClickListener {
            tvresult.text=""
            clearText()
            tvoperation.text=""
        })

        btc.setOnClickListener( View.OnClickListener {
            clearText()
        })

        btdel.setOnClickListener( View.OnClickListener {
            val temp:String  = number.text.toString()
            if(temp.isNotEmpty())
            number.setText(temp.subSequence(0,temp.length-1))
        })

        btequal.setOnClickListener(View.OnClickListener {
            if(tvoperation.text.isEmpty())
            {
                if(number.text.isEmpty())
                {
                    tvresult.text = number.text
                    clearText()
                }

            }
            else
            {
                calculateResult()
                tvoperation.text=""
            }

        })

    }

    private fun clearText()
    {
        number.setText("")
    }
    private fun calculateResult()
    {
        val n1 = tvresult.text.toString().toDouble()
        val  n2 = number.text.toString().toDouble()
        val result : Double
        result = when(tvoperation.text.toString()) {
            "+" -> n1+n2
            "-" -> n1-n2
            "*" -> n1*n2
            "%" -> (n1*n2)/100
            "rem" -> (n1 % n2)
            else -> n1/n2

        }
        tvresult.text = BigDecimal(result).setScale(10, RoundingMode.HALF_EVEN).toString()
        clearText()
    }
}
