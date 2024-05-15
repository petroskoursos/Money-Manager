package com.example.moneymanager20logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.moneymanager20logic.Database.presetation.MoneyEvent
import com.example.moneymanager20logic.Database.presetation.SavingState
import com.example.moneymanager20logic.ui.theme.Background
import com.example.moneymanager20logic.ui.theme.BoxColor

@Composable
fun AddItem(
    state: SavingState,
    navController: NavController,
    onEvent:(MoneyEvent) -> Unit
) {
    var isOpen by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            state.title.value = ""
            state.amount.value = ""
            //Navigate to add Screen
            // navController.navigate("AddScreen")
            isOpen = !isOpen
        }) {
            Text(text = "Add")
        }
        if(isOpen)
        {
            AddScreen(
                state = state,
                navController = navController,
                onEvent =onEvent,
                onClose = {isOpen=false}
            )
        }
        /////Lazy Grid///
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(state.saving.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(BoxColor)
                ) {
                    ItemSection(
                        state = state,
                        index = index,
                        onEvent = onEvent,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun Add(
    onClose:()->Unit,
    state:SavingState,
    onEvent: (MoneyEvent)-> Unit
) {
    Box(
        modifier = Modifier
            .background(Background)
            .fillMaxSize(0.5f)
    ) {
        Dialog(
            onDismissRequest = { onClose() },
            content = {
                Column(
                    modifier = Modifier
                        .padding(15.dp)

                ) {
                    OutlinedTextField(
                        value = state.title.value,
                        onValueChange = { state.title.value = it })
                    OutlinedTextField(
                        value = state.amount.value,
                        onValueChange = { state.amount.value })

                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Add item")
                    }

                }
            })
    }
}
