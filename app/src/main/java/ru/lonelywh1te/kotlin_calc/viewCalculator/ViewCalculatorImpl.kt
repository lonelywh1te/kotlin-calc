package ru.lonelywh1te.kotlin_calc.viewCalculator

import android.widget.Toast
import ru.lonelywh1te.kotlin_calc.calculator.CalculatorImpl
import ru.lonelywh1te.kotlin_calc.calculator.ICalculator
import ru.lonelywh1te.kotlin_calc.view.MainActivity
import java.lang.Error

const val MAX_DIGIT_VALUE = 15
class ViewCalculatorImpl: IViewCalculator {
    enum class Operation {
        NONE, SUM, SUBTRACTION, MULTIPLICATION, DIVISION
    }

    enum class State {
        WAITING, ENTERING, RESULT, ERROR
    }

    private val calculator: ICalculator = CalculatorImpl()
    private var resultNumber: String = "0"
    private var displayNumber: String = "0"
    private var currentState: State = State.WAITING
    private var currentOperation: Operation = Operation.NONE

    override fun getDisplayNumber(): String {
        if (displayNumber.endsWith(".0") && currentState != State.ENTERING) {
            displayNumber = displayNumber.dropLast(2)
        }

        return displayNumber
    }

    override fun digitButtonPressed(digit: CharSequence) {
        when(currentState) {
            State.WAITING -> displayNumber = ""
            State.ERROR -> return
            State.RESULT -> allClear()
            else -> {}
        }

        addDigit(digit)
    }

    override fun addDigit(digit: CharSequence) {
        if (displayNumber == "0") displayNumber = ""

        if (displayNumber.length < MAX_DIGIT_VALUE) {
            displayNumber += digit
        }

        currentState = State.ENTERING
    }

    override fun addComma() {
        if (currentState == State.ERROR) return

        if (!displayNumber.contains('.')) displayNumber += '.'

        currentState = State.ENTERING
    }

    override fun allClear() {
        resultNumber = "0"
        displayNumber = "0"
        currentState = State.WAITING
        currentOperation = Operation.NONE
    }

    override fun deleteLastDigit() {
        if (currentState == State.ERROR) return

        displayNumber = displayNumber.dropLast(1)
        if (displayNumber == "") {
            displayNumber = "0"
            currentState = State.WAITING
        }
    }

    override fun performOperation() {
        when (currentOperation) {
            Operation.SUM -> {
                resultNumber = calculator.sum(resultNumber.toDouble(), displayNumber.toDouble()).toString()
            }

            Operation.SUBTRACTION -> {
                resultNumber = calculator.subtraction(resultNumber.toDouble(), displayNumber.toDouble()).toString()
            }

            Operation.MULTIPLICATION -> {
                resultNumber = calculator.multiplication(resultNumber.toDouble(), displayNumber.toDouble()).toString()
            }

            Operation.DIVISION -> {
                resultNumber = calculator.division(resultNumber.toDouble(), displayNumber.toDouble()).toString()
            }

            Operation.NONE -> {}
        }

        if (isError()) currentState = State.ERROR
    }

    override fun sumBtnPressed() {
        if (currentState != State.WAITING && currentOperation != Operation.NONE) {
            performOperation()
            displayNumber = resultNumber
        } else {
            resultNumber = displayNumber
        }

        currentOperation = Operation.SUM
        currentState = State.WAITING

    }

    override fun subtractionBtnPressed() {
        if (currentState != State.WAITING && currentOperation != Operation.NONE) {
            performOperation()
            displayNumber = resultNumber
        } else {
            resultNumber = displayNumber
        }

        currentOperation = Operation.SUBTRACTION
        currentState = State.WAITING
    }

    override fun divisionBtnPressed() {
        if (currentState != State.WAITING && currentOperation != Operation.NONE) {
            performOperation()
            displayNumber = resultNumber
        } else {
            resultNumber = displayNumber
        }

        currentOperation = Operation.DIVISION
        currentState = State.WAITING
    }

    override fun multiplicationBtnPressed() {
        if (currentState != State.WAITING && currentOperation != Operation.NONE) {
            performOperation()
            displayNumber = resultNumber
        } else {
            resultNumber = displayNumber
        }

        currentOperation = Operation.MULTIPLICATION
        currentState = State.WAITING
    }

    override fun percentBtnPressed() {
        displayNumber = calculator.percent(displayNumber.toDouble()).toString()
        currentState = State.ENTERING
    }

    override fun equalBtnPressed() {
        if (currentState == State.ERROR) return

        if (currentState != State.WAITING) {
            performOperation()
        }

        displayNumber = resultNumber

        currentOperation = Operation.NONE
        if (currentState != State.ERROR) currentState = State.RESULT
    }

    override fun isError(): Boolean {
        return (resultNumber.toDouble().isInfinite() || resultNumber.toDouble().isNaN())
    }
}