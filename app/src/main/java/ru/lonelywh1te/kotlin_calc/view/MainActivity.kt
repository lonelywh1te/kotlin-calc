package ru.lonelywh1te.kotlin_calc.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
            val digitButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            val actionButtons = listOf(btnDivision, btnMultiplication, btnSubtraction, btnSum)

            btnEqual.setOnClickListener {
                viewCalculator.equalBtnPressed()

                resetActionButtons(actionButtons)
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

            btnInfo.setOnClickListener {
                startActivity(Intent(this@MainActivity, InfoActivity::class.java))
            }

            actionButtons.forEach { button -> button.setOnClickListener {
                    viewCalculator.operationButtonPressed(resources.getResourceEntryName(button.id))

                    setActiveActionButton(button, actionButtons)
                    updateDisplayNumber(viewCalculator)
                }
            }

            digitButtons.forEach { button -> button.setOnClickListener {
                    viewCalculator.digitButtonPressed(button.text)

                    resetActionButtons(actionButtons)
                    updateDisplayNumber(viewCalculator)
                }
            }
        }
    }

    private fun updateDisplayNumber(viewCalculator: IViewCalculator){
        val displayNumber = viewCalculator.getDisplayNumber()

        correctFontSize(displayNumber)

        binding.tvResultNumber.text = displayNumber
    }

    private fun setActiveActionButton(button: Button, actionButtons: List<Button>) {
        resetActionButtons(actionButtons)
        button.background.setTint(ContextCompat.getColor(this, R.color.activeColorButton))
        button.setTextColor(ContextCompat.getColor(this, R.color.white))
    }

    private fun resetActionButtons(buttons: List<Button>) {
        buttons.forEach { button ->
            button.background.setTint(ContextCompat.getColor(this, R.color.defaultColorButton))
            button.setTextColor(ContextCompat.getColor(this, R.color.activeColorButton))
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