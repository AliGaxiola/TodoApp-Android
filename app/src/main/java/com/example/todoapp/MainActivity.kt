package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.ui.todo.TodoScreen
import com.example.todoapp.ui.todo.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                val todoViewModel: TodoViewModel = hiltViewModel()

                TodoScreen(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = todoViewModel
                )
            }
        }
    }
}