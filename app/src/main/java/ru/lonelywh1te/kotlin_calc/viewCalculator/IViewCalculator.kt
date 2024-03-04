package ru.lonelywh1te.kotlin_calc.viewCalculator

interface IViewCalculator {
    fun getDisplayNumber(): String
    fun addDigit(digit: CharSequence)
    fun addComma()
    fun allClear()
    fun deleteLastDigit()
    fun performOperation()
    fun sumBtnPressed()
    fun equalBtnPressed()
}