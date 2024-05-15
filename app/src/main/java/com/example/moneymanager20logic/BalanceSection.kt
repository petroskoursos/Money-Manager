package com.example.moneymanager20logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.moneymanager20logic.Database.presetation.MoneyEvent
import com.example.moneymanager20logic.ui.theme.BoxColor
import com.example.moneymanager20logic.ui.theme.ButtonColor
import com.example.moneymanager20logic.ui.theme.TextWhite

@Composable
fun BalanceSection(
    onEvent:(MoneyEvent) ->Unit,
    sum:Double
){
    Box(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(BoxColor)
            .fillMaxWidth()
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(15.dp)
            //.padding(bottom = 50.dp)
        ) {
            Text(
                text = "Your Balance",
                color = TextWhite,
                style = MaterialTheme.typography.bodySmall
            )
            onEvent(MoneyEvent.Sum(0.0))
            Text(
                text = "$sum",
                color = TextWhite,
                style = MaterialTheme.typography.headlineLarge
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ){
                Button(
                    onClick = { /*TODO*/ },
                    elevation = ButtonDefaults.buttonElevation(15.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    shape = MaterialTheme.shapes.large){
                    Text(text = "Add Money")
                }
            }
        }
    }
}
