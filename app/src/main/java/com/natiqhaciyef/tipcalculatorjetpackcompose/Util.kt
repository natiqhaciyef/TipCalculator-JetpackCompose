package com.natiqhaciyef.tipcalculatorjetpackcompose

import androidx.compose.runtime.MutableState

fun calculateTip(input: Double, tipPercentage: Int) = input * tipPercentage / 100

fun calculatePerPerson(input: Double, tipPercentage: Int, splitBy: Int) =
    (calculateTip(input, tipPercentage) + input) / splitBy

fun calculatetipPercentage(sliderPositionState: MutableState<Float>) =
    (sliderPositionState.value * 100).toInt()