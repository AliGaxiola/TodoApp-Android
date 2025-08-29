package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.ui.todo.TodoScreen
import com.example.todoapp.ui.todo.TodoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.data.repository.TodoRepositoryImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                
                val database = TodoDatabase.getDatabase(applicationContext)
                val repository = TodoRepositoryImpl(database.todoDao())
                
                val todoViewModel: TodoViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            @Suppress("UNCHECKED_CAST")
                            return TodoViewModel(repository) as T
                        }
                    }
                )

                TodoScreen(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = todoViewModel
                )
            }
        }
    }
}
