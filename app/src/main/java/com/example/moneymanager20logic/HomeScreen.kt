package com.example.moneymanager20logic

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Update
import com.example.moneymanager20logic.Database.Daos.Saving
import com.example.moneymanager20logic.Database.presetation.MoneyEvent
import com.example.moneymanager20logic.Database.presetation.SavingState
import com.example.moneymanager20logic.Database.presetation.VMSaving
import com.example.moneymanager20logic.ui.theme.Background
import com.example.moneymanager20logic.ui.theme.CircleColor
import com.example.moneymanager20logic.ui.theme.TextWhite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext


@Composable
fun HomeScreen(
    state: SavingState,
    navController: NavController,
    onEvent:(MoneyEvent) -> Unit,
    sum: Double

){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Background)
    )
    {
        Column {
            GreetingSection()
            BalanceSection(onEvent=onEvent,sum)
            AddItem(state = state, navController =navController , onEvent =onEvent )
           // ItemSection(state = state, navController = navController, index = , onEvent = )
        }

    }

}

@Composable
fun Itemection(
    state:SavingState,
    navController: NavController,
    index: Int,
    onEvent: (MoneyEvent) -> Unit
){
    //Show the item
    var isOpen by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(15.dp)
    ) {
        Text(
            //Show the Title
            text = state.saving[index].title,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            //Show the Amount
            text = state.saving[index].amount.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(CircleColor)
            ) {
                Row {
                    //call the EditScreen
                    IconButton(onClick = {isOpen = !isOpen}) {
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")
                    }
                    //delete the box
                    IconButton(onClick = { onEvent(MoneyEvent.Delete(state.saving[index]))}) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription ="Delete" )
                    }
                }
                //edit Screen
                if(isOpen) {
                    EditScreen(
                        index=index,
                        state = state,
                        onEvent = onEvent,
                        onClose = {isOpen=false},
                       // vmSaving = viewModel(),
                        initialAmount = state.saving[index].amount.toString()
                    )
                }
            }
        }
    }
}
//
///////////////////////////////////////editeScreen//////////////////////////////////////////////////////
//@OptIn(DelicateCoroutinesApi::class)
//@Composable
//fun EdtScreen(
//    onClose:()-> Unit,
//    index: Int,
//    state: SavingState,
//    initialAmount:String,
//    onEvent: (MoneyEvent) -> Unit
//){
//    // here i make the previous value to add it with the new value
//    var sum :Double
//    val temp:String = state.saving[index].amount.toString()
//    Column() {
//        Dialog(
//            onDismissRequest = {onClose()},
//            content ={
//                Column(
//                    modifier = Modifier
//                        .fillMaxHeight(0.4f)
//                        .background(Background)
//                ) {
//                    Column(modifier = Modifier
//                        .padding(15.dp)
//                        ) {
//                        Text(
//                            text = state.saving[index].title,
//                            color = TextWhite,
//                            style = MaterialTheme.typography.headlineMedium
//                        )
//                        Spacer(modifier = Modifier.padding(top = 8.dp))
//                        OutlinedTextField(
//                            //shows the current number that is saved in it
//                            singleLine = true,
//                            placeholder = { Text(initialAmount) },
//                            value = state.amount.value,
//                            onValueChange = { state.amount.value = it },
//                            textStyle = TextStyle(TextWhite)
//                        )
//                        Row(
//                            modifier = Modifier
//                                .fillMaxHeight(),
//                            verticalAlignment = Alignment.Bottom,
//                            horizontalArrangement = Arrangement.End
//                        ) {
//                            Button(
//                                onClick = {
//                                    // here the calculation happens
//                                    sum = temp.toDouble() + state.amount.value.toDouble()
//
//                                    onEvent(
//                                        MoneyEvent.Update(
//                                            //update the value
//                                            amount = sum,
//                                            title = state.saving[index].title
//                                        )
//                                    )
//
//                                    onClose()
//                                },
//                                colors = ButtonDefaults.buttonColors(CircleColor)
//                            ) {
//                                Text(text = "Update", color = Color.Black)
//                            }
//
//                        }
//                    }
//                }
//            }
//        )
//    }
//}

