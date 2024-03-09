package ru.lonelywh1te.kotlin_calc.viewCalculator

import ru.lonelywh1te.kotlin_calc.calculator.CalculatorImpl
import ru.lonelywh1te.kotlin_calc.calculator.ICalculator

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

    override fun addComma(comma: CharSequence) {
        if (currentState == State.ERROR) return

        if (!displayNumber.contains(comma)) displayNumber += comma

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

    override fun prepareOperation() {
        if (currentState != State.WAITING && currentOperation != Operation.NONE) {
            performOperation()
            displayNumber = resultNumber
        } else {
            resultNumber = displayNumber
        }
    }

    override fun operationButtonPressed(id: String) {
        prepareOperation()

        currentOperation = when(id) {
            "btnSum" -> Operation.SUM
            "btnSubtraction" -> Operation.SUBTRACTION
            "btnDivision" -> Operation.DIVISION
            "btnMultiplication" -> Operation.MULTIPLICATION
            else -> Operation.NONE
        }
        currentState = State.WAITING
    }

    override fun performOperation() {
        val resultNumberDouble = resultNumber.toDouble()
        val displayNumberDouble = displayNumber.toDouble()

        resultNumber = when (currentOperation) {
            Operation.SUM -> calculator.sum(resultNumberDouble, displayNumberDouble).toString()
            Operation.SUBTRACTION -> calculator.subtraction(resultNumberDouble, displayNumberDouble).toString()
            Operation.MULTIPLICATION -> calculator.multiplication(resultNumberDouble, displayNumberDouble).toString()
            Operation.DIVISION -> calculator.division(resultNumberDouble, displayNumberDouble).toString()
            Operation.NONE -> displayNumber
        }

        if (isError()) currentState = State.ERROR
    }

    override fun percentBtnPressed() {
        val resultNumberDouble = resultNumber.toDouble()
        val displayNumberDouble = displayNumber.toDouble()

        displayNumber = if (resultNumber == "0") {
            calculator.percent(displayNumberDouble, 1.0).toString()
        } else {
            calculator.percent(resultNumberDouble, displayNumberDouble).toString()
        }

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