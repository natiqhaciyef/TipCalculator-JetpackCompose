package com.natiqhaciyef.tipcalculatorjetpackcompose

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.tipcalculatorjetpackcompose.components.*
import com.natiqhaciyef.tipcalculatorjetpackcompose.ui.theme.TipCalculatorJetpackComposeTheme
import com.natiqhaciyef.tipcalculatorjetpackcompose.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Screen()
            }
        }
    }
}

@Composable
fun Screen() {
    val splitBy = remember { mutableStateOf(1) }
    val totalPerPersonState = remember {
        mutableStateOf(0.0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopHeader(data = totalPerPersonState.value.toString())
            Body(splitBy, totalPerPersonState)
        }
    }
}


//@Preview
@Composable
fun TopHeader(data: String = "0") {
    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.22f)
                .padding(15.dp)
                .clip(RoundedCornerShape(15.dp)),
            color = Color(0xffe1bee7)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val totalPerPerson =
                    if (data.isNotEmpty()) "%.2f".format(data.toDouble()) else "0.0"
                CustomText(
                    text = "Total Per Person",
                    textSize = 23,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 15.dp),
                    fontWeight = FontWeight.Bold
                )
                CustomTextBill(
                    text = totalPerPerson,
                    textSize = 36,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}


//@Preview
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Body(splitBy: MutableState<Int>,
         totalPerPersonState: MutableState<Double>) {
    val input = remember { mutableStateOf("0.0") }

    val sliderPositionState = remember {
        mutableStateOf(0.0f)
    }
    val tipPercentage = calculatetipPercentage(sliderPositionState)

    val validState = remember(input) {
        input.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(15.dp)
                .border(
                    width = 1.5.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                CustomTextField(
                    value = input.value,
                    onAction = KeyboardActions {
                        if (!validState) return@KeyboardActions
                        keyboardController?.hide()
                    },
                    function = {
                        input.value = it
                    }
                )
                if (validState) {
                    BodySplitPart(input, splitBy)
                    BodyTipPart(input, tipPercentage)
                    BodySeekBarPart(tipPercentage = tipPercentage,
                        sliderPositionState = sliderPositionState,
                    splitBy = splitBy.value,
                        input = input,
                        totalPerPersonState = totalPerPersonState)
                } else
                    Box {}


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorJetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Screen()
    }
}
