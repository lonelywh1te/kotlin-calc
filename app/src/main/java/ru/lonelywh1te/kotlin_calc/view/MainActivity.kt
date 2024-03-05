package ru.lonelywh1te.kotlin_calc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import ru.lonelywh1te.kotlin_calc.R
import ru.lonelywh1te.kotlin_calc.databinding.ActivityMainBinding
import ru.lonelywh1te.kotlin_calc.viewCalculator.IViewCalculator
import ru.lonelywh1te.kotlin_calc.viewCalculator.ViewCalculatorImpl

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        val viewCalculator: IViewCalculator = ViewCalculatorImpl()

        setContentView(view)
        setClickEventListeners(viewCalculator)
        updateDisplayNumber(viewCalculator)
    }

    private fun setClickEventListeners(viewCalculator: IViewCalculator){
        with(binding) {
            val actionButtons = listOf(btnDivision, btnMultiplication, btnSubstraction, btnSum)

            btnEqual.setOnClickListener {
                viewCalculator.equalBtnPressed()
                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btnDivision.setOnClickListener {
                viewCalculator.divisionBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnDivision)
                updateDisplayNumber(viewCalculator)
            }

            btnMultiplication.setOnClickListener {
                viewCalculator.multiplicationBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnMultiplication)
                updateDisplayNumber(viewCalculator)
            }

            btnSubstraction.setOnClickListener {
                viewCalculator.subtractionBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnSubstraction)
                updateDisplayNumber(viewCalculator)
            }

            btnSum.setOnClickListener {
                viewCalculator.sumBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnSum)
                updateDisplayNumber(viewCalculator)
            }

            btnPercent.setOnClickListener {
                viewCalculator.percentBtnPressed()
                updateDisplayNumber(viewCalculator)
            }

            btnDelete.setOnClickListener {
                viewCalculator.deleteLastDigit()
                updateDisplayNumber(viewCalculator)
            }

            btnAllClear.setOnClickListener {
                viewCalculator.allClear()
                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btnComma.setOnClickListener {
                viewCalculator.addComma()
                updateDisplayNumber(viewCalculator)
            }

            btn0.setOnClickListener {
                viewCalculator.addDigit(btn0.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn1.setOnClickListener {
                viewCalculator.addDigit(btn1.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn2.setOnClickListener {
                viewCalculator.addDigit(btn2.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn3.setOnClickListener {
                viewCalculator.addDigit(btn3.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn4.setOnClickListener {
                viewCalculator.addDigit(btn4.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn5.setOnClickListener {
                viewCalculator.addDigit(btn5.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn6.setOnClickListener {
                viewCalculator.addDigit(btn6.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn7.setOnClickListener {
                viewCalculator.addDigit(btn7.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn8.setOnClickListener {
                viewCalculator.addDigit(btn8.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn9.setOnClickListener {
                viewCalculator.addDigit(btn9.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }
        }
    }

    private fun updateDisplayNumber(viewCalculator: IViewCalculator){
        val displayNumber = viewCalculator.getDisplayNumber()
        correctFontSize(displayNumber)

        binding.tvResultNumber.text = displayNumber
    }

    private fun setActiveActionButton(button: Button) {
        button.background.setTint(ContextCompat.getColor(this, R.color.activeColorButton))
        button.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun resetActionButtons(buttons: List<Button>) {
        buttons.forEach {
            it.background.setTint(ContextCompat.getColor(this, R.color.defaultColorButton))
            it.setTextColor(ContextCompat.getColor(this, R.color.activeColorButton))
        }
    }

    private fun correctFontSize(displayNumber: String) {
        binding.tvResultNumber.textSize = when {
            (displayNumber.length <= 10) -> 64.0f
            (displayNumber.length <= 15) -> 44.0f
            else -> 35.0f
        }
    }
}