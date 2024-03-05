package ru.lonelywh1te.kotlin_calc.calculator

const val DOUBLE_PRECISION = 10
class CalculatorImpl: ICalculator {
    override fun fixDouble(number: Double): Double {
        return "%.${DOUBLE_PRECISION}g".format(number).replace(',', '.').toDouble()
    }

    override fun sum(a: Double, b: Double): Double {
        return a + b
    }

    override fun subtraction(a: Double, b: Double): Double {
        return a - b
    }

    override fun division(a: Double, b: Double): Double {
        return fixDouble(a / b)
    }

    override fun multiplication(a: Double, b: Double): Double {
        return a * b
    }

    override fun percent(number: Double): Double {
        return division(number, 100.0)
    }
}