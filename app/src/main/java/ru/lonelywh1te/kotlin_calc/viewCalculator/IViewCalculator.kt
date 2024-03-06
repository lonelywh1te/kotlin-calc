package ru.lonelywh1te.kotlin_calc.viewCalculator

interface IViewCalculator {
    fun getDisplayNumber(): String
    fun digitButtonPressed(digit: CharSequence)
    fun addDigit(digit: CharSequence)
    fun addComma()
    fun allClear()
    fun deleteLastDigit()
    fun performOperation()
    fun equalBtnPressed()
    fun sumBtnPressed()
    fun subtractionBtnPressed()
    fun divisionBtnPressed()
    fun multiplicationBtnPressed()
    fun percentBtnPressed()
    fun isError(): Boolean
}