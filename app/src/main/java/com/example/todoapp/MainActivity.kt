package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.components.AddTaskDialog
import com.example.todoapp.ui.components.TodoFAB
import com.example.todoapp.ui.components.TodoTopAppBar
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.ui.todo.TodoScreen
import com.example.todoapp.ui.todo.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                var showDialog by remember { mutableStateOf(false) }

                val todoViewModel: TodoViewModel = viewModel()

                Scaffold(
                    topBar = { TodoTopAppBar() },
                    floatingActionButton = { TodoFAB { showDialog = true } }
                ) { innerPadding ->
                    TodoScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        viewModel = todoViewModel
                    )

                    AddTaskDialog(
                        showDialog = showDialog, onDismissDialog = {
                            showDialog = false
                        },
                        onAddTask = { title, description ->
                            todoViewModel.addTodo(title, description)
                        }
                    )
                }
            }
        }
    }
}