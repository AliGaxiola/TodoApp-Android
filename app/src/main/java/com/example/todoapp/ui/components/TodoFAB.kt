package com.example.todoapp.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R

@Composable
fun TodoFAB(showDialog: () -> Unit) {
    FloatingActionButton(
        onClick = { showDialog() },
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp,
            hoveredElevation = 8.dp,
            focusedElevation = 8.dp
        )
    ) {
        Icon(
            painterResource(R.drawable.ic_add),
            contentDescription = "Add"
        )
    }
}