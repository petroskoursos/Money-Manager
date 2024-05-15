package com.example.moneymanager20logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moneymanager20logic.Database.presetation.MoneyEvent
import com.example.moneymanager20logic.Database.presetation.SavingState
import com.example.moneymanager20logic.ui.theme.CircleColor
import com.example.moneymanager20logic.ui.theme.TextWhite

@Composable
fun ItemSection(
    state: SavingState,
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
            color = TextWhite,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            //Show the Amount
            text = state.saving[index].amount.toString(),
            color = TextWhite,
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