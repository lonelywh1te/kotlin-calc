package ru.lonelywh1te.kotlin_calc.viewCalculator

import ru.lonelywh1te.kotlin_calc.calculator.CalculatorImpl
import ru.lonelywh1te.kotlin_calc.calculator.ICalculator

const val MAX_DIGIT_VALUE = 15
class ViewCalculatorImpl: IViewCalculator {
    enum class Operation {
        NONE, SUM, SUBTRACTION, MULTIPLICATION, DIVISION
    }

    enum class State {
        WAITING, ENTERING, RESULT
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

    override fun addDigit(digit: CharSequence) {
        if (currentState == State.RESULT) allClear()
        if (currentState == State.WAITING || displayNumber == "0") displayNumber = ""

        if (displayNumber.length < MAX_DIGIT_VALUE) {
            displayNumber += digit
        }

        currentState = State.ENTERING
    }

    override fun addComma() {
        if (!displayNumber.contains('.')) displayNumber += '.'
    }

    override fun allClear() {
        resultNumber = "0"
        displayNumber = "0"
        currentState = State.WAITING
        currentOperation = Operation.NONE
    }

    override fun deleteLastDigit() {
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
        if (currentState != State.WAITING) {
            performOperation()
        }

        displayNumber = resultNumber

        currentOperation = Operation.NONE
        currentState = State.RESULT
    }
}