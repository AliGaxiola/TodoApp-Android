package com.example.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()
    private var nextId = 1

    // Add new todo
    fun addTodo(title: String, description: String) {
        viewModelScope.launch {
            val newTodo = TodoItem(
                id = nextId++,
                title = title,
                description = description,
                isComplete = false
            )

            _todos.value = _todos.value + newTodo
        }
    }

    // Toggle completion status
    fun toggleComplete(id: Int) {
        viewModelScope.launch {
            _todos.value = _todos.value.map { todo ->
                if (todo.id == id) {
                    todo.copy(isComplete = !todo.isComplete)
                } else {
                    todo
                }
            }
        }
    }

    // Remove todo
    fun removeTodo(id: Int) {
        viewModelScope.launch {
            _todos.value = _todos.value.filter { it.id != id }
        }
    }

    // Edit todo
    fun editTodo(id: Int, newTitle: String, newDescription: String) {
        viewModelScope.launch {
            _todos.value = _todos.value.map { todo ->
                if (todo.id == id) {
                    todo.copy(title = newTitle, description = newDescription)
                } else {
                    todo
                }
            }
        }
    }
}