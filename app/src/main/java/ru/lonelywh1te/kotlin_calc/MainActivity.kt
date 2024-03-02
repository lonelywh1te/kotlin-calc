package ru.lonelywh1te.kotlin_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.lonelywh1te.kotlin_calc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        val viewCalculator = ViewCalculator(binding)
        setClickEventListeners(viewCalculator)
    }

    private fun setClickEventListeners(viewCalculator: ViewCalculator){
        with(binding) {
            btnDelete.setOnClickListener {
                viewCalculator.deleteLastDigit()
            }

            btnAllClear.setOnClickListener {
                viewCalculator.allClear()
            }

            btnComma.setOnClickListener {
                viewCalculator.addComma()
            }

            btn0.setOnClickListener {
                viewCalculator.addDigit(btn0.text)
            }

            btn1.setOnClickListener {
                viewCalculator.addDigit(btn1.text)
            }

            btn2.setOnClickListener {
                viewCalculator.addDigit(btn2.text)
            }

            btn3.setOnClickListener {
                viewCalculator.addDigit(btn3.text)
            }

            btn4.setOnClickListener {
                viewCalculator.addDigit(btn4.text)
            }

            btn5.setOnClickListener {
                viewCalculator.addDigit(btn5.text)
            }

            btn6.setOnClickListener {
                viewCalculator.addDigit(btn6.text)
            }

            btn7.setOnClickListener {
                viewCalculator.addDigit(btn7.text)
            }

            btn8.setOnClickListener {
                viewCalculator.addDigit(btn8.text)
            }

            btn9.setOnClickListener {
                viewCalculator.addDigit(btn9.text)
            }
        }
    }
}

class ViewCalculator(private val view: ActivityMainBinding) {
    private var displayNumber: String = "0"

    private fun updateDisplayNumber(){
        if (displayNumber == "") displayNumber = "0"
        view.tvResultNumber.text = displayNumber
    }

    fun addDigit(digit: CharSequence) {
        if (displayNumber == "0") displayNumber = ""

        if (displayNumber.length < 12) {
            displayNumber += digit
            updateDisplayNumber()
        }
    }

    fun addComma() {
        if (!displayNumber.contains('.')) displayNumber += '.'
        updateDisplayNumber()
    }

    fun allClear() {
        displayNumber = "0"
        updateDisplayNumber()
    }

    fun deleteLastDigit() {
        displayNumber = displayNumber.dropLast(1)
        updateDisplayNumber()
    }
}