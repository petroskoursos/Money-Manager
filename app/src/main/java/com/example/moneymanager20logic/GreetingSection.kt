package com.example.moneymanager20logic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneymanager20logic.ui.theme.TextWhite

@Composable
fun GreetingSection(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column {
            Text(
                text = "Welcome back",
                color= TextWhite,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Hello, Peter",
                color = TextWhite,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}