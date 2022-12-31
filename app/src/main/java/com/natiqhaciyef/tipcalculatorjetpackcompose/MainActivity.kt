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
    var inputBill = remember { mutableStateOf("0.0") }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopHeader(data = inputBill.value)
            Body(input = inputBill.value) { inputBill.value = it }
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
fun Body(input: String, function: (String) -> Unit) {
    val sliderPositionState = remember {
        mutableStateOf(0.0f)
    }
    val validState = remember(input) {
        input.trim().isNotEmpty()
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
                    value = input,
                    onAction = KeyboardActions {
                        if (!validState) return@KeyboardActions
                        keyboardController?.hide()
                    },
                    function = function
                )
                if (validState) {
                    BodySplitPart()
                    BodyTipPart()
                    BodySeekBarPart(sliderPositionState = sliderPositionState)
                } else {
                    Box {}
                }

            }
        }
    }
}


@Composable
fun BodySeekBarPart(sliderPositionState: MutableState<Float>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "33 %",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Slider(modifier = Modifier.padding(horizontal = 10.dp ),
            value = sliderPositionState.value,
            onValueChange = { newVal ->
                sliderPositionState.value = newVal
            })
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
