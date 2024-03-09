package ru.lonelywh1te.kotlin_calc.calculator

interface ICalculator {
    fun fixDouble(number: Double): Double
    fun sum(a: Double, b: Double): Double
    fun subtraction(a: Double, b: Double): Double
    fun division(a: Double, b: Double): Double
    fun multiplication(a: Double, b: Double): Double
    fun percent(number: Double, percent: Double): Double
}