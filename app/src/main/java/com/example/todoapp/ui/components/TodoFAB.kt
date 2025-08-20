package com.example.todoapp.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.todoapp.R

@Composable
fun TodoFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    ){
        Icon(
            painterResource(R.drawable.ic_add),
            contentDescription = "Add"
        )
    }
}