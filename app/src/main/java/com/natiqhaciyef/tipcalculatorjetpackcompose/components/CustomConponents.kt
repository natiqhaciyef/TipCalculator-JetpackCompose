package com.natiqhaciyef.tipcalculatorjetpackcompose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.tipcalculatorjetpackcompose.calculatePerPerson
import com.natiqhaciyef.tipcalculatorjetpackcompose.calculateTip
import com.natiqhaciyef.tipcalculatorjetpackcompose.widgets.RoundIconButton

@Composable
fun CustomText(
    text: String,
    textSize: Int = 18,
    alignment: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4,
        fontSize = textSize.sp,
        textAlign = alignment,
        fontWeight = fontWeight,
        modifier = modifier
    )
}


@Composable
fun CustomTextBill(
    text: String,
    textSize: Int = 18,
    alignment: TextAlign = TextAlign.Center,
    fontWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$ $text",
        style = MaterialTheme.typography.h4,
        fontSize = textSize.sp,
        textAlign = alignment,
        fontWeight = fontWeight,
        modifier = modifier
    )
}


@Composable
fun CustomTextField(
    value: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    function: (String) -> Unit = { }
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp, 0.dp),
        value = value,
        onValueChange = function,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black

        ),
        label = { Text(text = "Enter Bill") },
        placeholder = { Text(text = "0.00") },
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.AttachMoney,
                contentDescription = "Money"
            )
        }
    )
}


@Composable
fun BodySplitPart(input: MutableState<String>, splitBy: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Split",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 15.dp, top = 10.dp)
        )
        Spacer(modifier = Modifier.width(130.dp))
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundIconButton(
                imageVector = Icons.Default.Remove,
                clickEvent = {
                    try {
                        if (splitBy.value > 1)
                            splitBy.value -= 1

                        input.value = "${input.value.toInt() / splitBy.value}"
                    } catch (e: Exception) {
                    }
                }
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .align(Alignment.CenterVertically),
                text = "${splitBy.value}"
            )

            RoundIconButton(
                modifier = Modifier,
                imageVector = Icons.Default.Add,
                clickEvent = {
                    if (splitBy.value < 100)
                        splitBy.value += 1
                }
            )
        }
    }
}


@Composable
fun BodyTipPart(input: MutableState<String>, tipPercentage: Int) {
    var tip = "0.0"
    try {
        tip = "%.2f".format(calculateTip(input.value.toDouble(), tipPercentage))
    } catch (e: Exception) {
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "Tip",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 15.dp)
        )
        Spacer(modifier = Modifier.width(190.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp),
            text = "$$tip",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )
    }
}

@Composable
fun BodySeekBarPart(
    splitBy: Int,
    input: MutableState<String>,
    totalPerPersonState: MutableState<Double>,
    tipPercentage: Int,
    sliderPositionState: MutableState<Float>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$tipPercentage%",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(
            modifier = Modifier.padding(horizontal = 15.dp),
            value = sliderPositionState.value,
            onValueChange = { newVal ->
                sliderPositionState.value = newVal
                totalPerPersonState.value =
                    calculatePerPerson(input.value.toDouble(), tipPercentage, splitBy)
            },
            steps = 100
        )
    }
}