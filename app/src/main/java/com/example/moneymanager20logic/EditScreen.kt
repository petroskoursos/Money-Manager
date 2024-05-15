package com.example.moneymanager20logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.moneymanager20logic.Database.presetation.MoneyEvent
import com.example.moneymanager20logic.Database.presetation.SavingState
import com.example.moneymanager20logic.ui.theme.Background
import com.example.moneymanager20logic.ui.theme.CircleColor
import com.example.moneymanager20logic.ui.theme.TextWhite

@Composable
fun EditScreen(
    onClose:()-> Unit,
    index: Int,
    state: SavingState,
    initialAmount:String,
    onEvent: (MoneyEvent) -> Unit
){
    var sum : Double
    val temp : String= state.saving[index].amount.toString()
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize(0.5f)
    ) {
        Dialog(
            onDismissRequest = {onClose()},
            content ={
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .background(Background)
                ) {
                    Column(modifier = Modifier
                        .padding(15.dp)
                    ) {
                        Text(
                            text = state.saving[index].title,
                            color = TextWhite,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        OutlinedTextField(
                            //shows the current number that is saved in it
                            singleLine = true,
                            placeholder = { Text(initialAmount) },
                            value = state.amount.value,
                            onValueChange = { state.amount.value = it },
                            textStyle = TextStyle(TextWhite)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = {
                                    // here the calculation happens
                                    if(state.amount.value.isNotEmpty()) {
                                        sum = temp.toDouble() + state.amount.value.toDouble()
                                        onEvent(
                                            MoneyEvent.Update(
                                                //update the value
                                                amount = sum,
                                                title = state.saving[index].title
                                            )
                                        )
                                    }
                                    state.amount.value=""
                                    state.title.value=""
                                    onClose()
                                },
                                colors = ButtonDefaults.buttonColors(CircleColor)
                            ) {
                                Text(text = "Update", color = Color.Black)
                            }

                        }
                    }
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EditScreenPreview(){
    //EditScreen(navController = na)
}