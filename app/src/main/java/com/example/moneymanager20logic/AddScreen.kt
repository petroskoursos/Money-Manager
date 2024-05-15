package com.example.moneymanager20logic

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.moneymanager20logic.Database.presetation.MoneyEvent
import com.example.moneymanager20logic.Database.presetation.SavingState
import com.example.moneymanager20logic.ui.theme.Background
import com.example.moneymanager20logic.ui.theme.ButtonColor
import com.example.moneymanager20logic.ui.theme.CircleColor
import com.example.moneymanager20logic.ui.theme.TextWhite
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KFunction1


@Composable
fun AddScreen(
    state: SavingState,
    navController: NavController,
    onEvent: (MoneyEvent)-> Unit,
    onClose: ()-> Unit
) {
    val context = LocalContext.current
    Column {
        Dialog(
            onDismissRequest = { onClose() },
            content = {
                Box(
                    modifier = Modifier
                        .background(Background)
                        .fillMaxWidth()
                        .fillMaxSize(0.4f)
                        .padding(15.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxSize()
                    ) {
                        OutlinedTextField(
                            value = state.title.value,
                            onValueChange = { state.title.value = it },
                            textStyle = TextStyle(TextWhite),
                            placeholder = { Text(text = "Enter Title")}
                        )

                        Spacer(modifier = Modifier.padding(bottom = 15.dp))

                        OutlinedTextField(
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            value = state.amount.value,
                            onValueChange = { state.amount.value = it },
                            textStyle = TextStyle(TextWhite),
                            placeholder = { Text(text = "Enter Amount")},
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = {
                                    if (state.amount.value.isNotEmpty() && state.title.value.isNotEmpty()) {
                                        onEvent(
                                            MoneyEvent.Save(
                                                amount = state.amount.value,
                                                title = state.title.value
                                            )
                                        )
                                        state.amount.value=""
                                        onClose()
                                    } else {

                                        val text = "Enter Both Values"
                                        val duration = Toast.LENGTH_SHORT

                                        Toast.makeText(context, text, duration).show()


                                    }

                                },
                                colors = ButtonDefaults.buttonColors(
                                    CircleColor
                                )
                            ) {
                                Text(text = "Add Item", color = Color.Black)
                            }
                        }
                    }

                }
            })
    }
}

//    Scaffold(
//        //float Button
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                //call the Save Event runBlocking{
//                       onEvent(MoneyEvent.Save(
//                            title = state.title.value,
//                            amount = state.amount.value
//                        ))
//
//                navController.popBackStack()
//            }) {
//                Icon(imageVector = Icons.Rounded.Check, contentDescription ="Check")
//            }
//        }
//    ){paddingValues ->
//        Column(modifier = Modifier
//            .padding(paddingValues)
//            .fillMaxSize()
//        ){
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value =state.title.value,
//                onValueChange = {
//                    state.title.value=it
//                },
//                textStyle = TextStyle(
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 17.sp
//                ),
//                placeholder = {
//                    Text(text = "Title")
//                }
//
//            )
//
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                value =state.amount.value,
//                onValueChange = {
//                    state.amount.value=it
//                },
//                textStyle = TextStyle(
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 17.sp
//                ),
//                placeholder = {
//                    Text(text = "Description")
//                }
//
//            )
//
//        }
//
//    }
