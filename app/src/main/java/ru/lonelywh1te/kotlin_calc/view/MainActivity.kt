package ru.lonelywh1te.kotlin_calc.view

import android.os.Bundle
import android.view.HapticFeedbackConstants
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
            val actionButtons = listOf(btnDivision, btnMultiplication, btnSubstraction, btnSum)

            btnEqual.setOnClickListener {
                btnEqual.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                viewCalculator.equalBtnPressed()

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btnDivision.setOnClickListener {
                btnDivision.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.divisionBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnDivision)
                updateDisplayNumber(viewCalculator)
            }

            btnMultiplication.setOnClickListener {
                btnMultiplication.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.multiplicationBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnMultiplication)
                updateDisplayNumber(viewCalculator)
            }

            btnSubstraction.setOnClickListener {
                btnSubstraction.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.subtractionBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnSubstraction)
                updateDisplayNumber(viewCalculator)
            }

            btnSum.setOnClickListener {
                btnSum.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.sumBtnPressed()

                resetActionButtons(actionButtons)
                setActiveActionButton(binding.btnSum)
                updateDisplayNumber(viewCalculator)
            }

            btnPercent.setOnClickListener {
                btnPercent.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.percentBtnPressed()

                updateDisplayNumber(viewCalculator)
            }

            btnDelete.setOnClickListener {
                btnDelete.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.deleteLastDigit()

                updateDisplayNumber(viewCalculator)
            }

            btnAllClear.setOnClickListener {
                btnAllClear.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.allClear()

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btnComma.setOnClickListener {
                btnComma.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addComma()

                updateDisplayNumber(viewCalculator)
            }

            btn0.setOnClickListener {
                btn0.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn0.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn1.setOnClickListener {
                btn1.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn1.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn2.setOnClickListener {
                btn2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn2.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn3.setOnClickListener {
                btn3.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn3.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn4.setOnClickListener {
                btn4.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn4.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn5.setOnClickListener {
                btn5.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn5.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn6.setOnClickListener {
                btn6.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn6.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn7.setOnClickListener {
                btn7.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn7.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn8.setOnClickListener {
                btn8.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                viewCalculator.addDigit(btn8.text)

                resetActionButtons(actionButtons)
                updateDisplayNumber(viewCalculator)
            }

            btn9.setOnClickListener {
                btn9.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

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