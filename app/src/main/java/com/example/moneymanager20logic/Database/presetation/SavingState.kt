package com.example.moneymanager20logic.Database.presetation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.moneymanager20logic.Database.table.Entities


data class SavingState(

    val saving: List<Entities> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val amount: MutableState<String> = mutableStateOf(""),
)