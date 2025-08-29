package com.example.todoapp.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.todoapp.domain.model.TodoItem
import com.example.todoapp.ui.components.AddTaskDialog
import com.example.todoapp.ui.components.TodoFAB
import com.example.todoapp.ui.components.TodoCard
import com.example.todoapp.ui.components.TodoTopAppBar

@Composable
fun TodoScreen(modifier: Modifier = Modifier, viewModel: TodoViewModel = viewModel()) {
    val todos by viewModel.todos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val (editTodo, setEditTodo) = remember { mutableStateOf<TodoItem?>(null) }

    Scaffold(
        floatingActionButton = {
            TodoFAB {
                setEditTodo(null)
                setShowDialog(true)
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { TodoTopAppBar() }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                todos.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No tasks available. Add a new task!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                else -> {
                    val hasCompleted = todos.any { it.isCompleted }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { viewModel.clearAllTodos() }) {
                            Text("Clear all")
                        }
                        if (hasCompleted) {
                            TextButton(onClick = { viewModel.clearCompletedTodos() }) {
                                Text("Clear completed")
                            }
                        }
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(todos) { todo ->
                            TodoCard(
                                title = todo.title,
                                description = todo.description,
                                isCompleted = todo.isCompleted,
                                onToggleComplete = { viewModel.toggleComplete(todo.id) },
                                onClickDelete = { viewModel.removeTodo(todo.id) },
                                onClickEdit = {
                                    setEditTodo(todo)
                                    setShowDialog(true)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    AddTaskDialog(
        showDialog = showDialog,
        onDismissDialog = {
            setShowDialog(false)
            setEditTodo(null)
        },
        onConfirm = { title, description ->
            if (editTodo == null) {
                viewModel.addTodo(title, description)
            } else {
                viewModel.editTodo(editTodo.id, title, description)
            }
            setShowDialog(false)
            setEditTodo(null)
        },
        initialTitle = editTodo?.title ?: "",
        initialDescription = editTodo?.description ?: "",
        isEditMode = editTodo != null
    )
}