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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun CustomTextField(value: String,
                    onAction: KeyboardActions = KeyboardActions.Default,
                    function: (String) -> Unit = { }) {
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
        keyboardActions = onAction ,
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
fun BodySplitPart() {
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
                clickEvent = { }
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .align(Alignment.CenterVertically),
                text = "0"
            )

            RoundIconButton(
                modifier = Modifier,
                imageVector = Icons.Default.Add,
                clickEvent = { }
            )
        }
    }
}


@Composable
fun BodyTipPart() {
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
            text = "$33.00",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )
    }
}

