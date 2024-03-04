package ru.lonelywh1te.kotlin_calc.viewCalculator

import ru.lonelywh1te.kotlin_calc.calculator.CalculatorImpl
import ru.lonelywh1te.kotlin_calc.calculator.ICalculator
class ViewCalculatorImpl: IViewCalculator {
    enum class Operation {
        NONE, SUM
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
        return displayNumber
    }

    override fun addDigit(digit: CharSequence) {
        if (currentState == State.RESULT) allClear()
        if (currentState == State.WAITING) displayNumber = ""

        if (displayNumber.length < 12) {
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
        when(currentOperation){
            Operation.SUM -> resultNumber = calculator.sum(resultNumber.toDouble(), displayNumber.toDouble()).toString()
            else -> {}
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

    override fun equalBtnPressed() {
        if (currentState != State.WAITING) {
            performOperation()
        }

        displayNumber = resultNumber

        currentOperation = Operation.NONE
        currentState = State.RESULT
    }
}