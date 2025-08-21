package com.example.todoapp.ui.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todoapp.ui.components.TodoCard

@Composable
fun TodoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TodoCard(
            title = "Sample Todo Item",
            description = "This is a description of the todo item. It can be a longer text that explains what needs to be done."
        )
        TodoCard(
            title = "Another Todo Item",
            description = "This is another todo item. You can add as many items as you like.",
            isComplete = true
        )
        TodoCard(
            title = "Yet Another Todo Item",
            description = "This item is still pending. Make sure to complete it soon."
        )

    }
}