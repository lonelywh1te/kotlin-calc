package ru.lonelywh1te.kotlin_calc.calculator

class CalculatorImpl: ICalculator {
    override fun sum(a: Double, b: Double): Double {
        return a + b
    }

    override fun subtraction(a: Double, b: Double): Double {
        return a - b
    }

    override fun division(a: Double, b: Double): Double {
        TODO("Not yet implemented")
    }

    override fun multiplication(a: Double, b: Double): Double {
        TODO("Not yet implemented")
    }

    override fun percent(number: Double): Double {
        return number / 100
    }
}