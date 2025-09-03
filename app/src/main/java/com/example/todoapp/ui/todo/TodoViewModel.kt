package com.example.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.TodoItem
import com.example.todoapp.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos: StateFlow<List<TodoItem>> = repository.getAllTodos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTodos().collectLatest {
                _isLoading.value = false
            }
        }
    }

    fun addTodo(title: String, description: String) {
        viewModelScope.launch {
            val newTodo = TodoItem(
                title = title,
                description = description,
                isCompleted = false
            )
            repository.insertTodo(newTodo)
        }
    }

    fun toggleComplete(id: Int) {
        viewModelScope.launch {
            val currentList = todos.value
            val todo = currentList.find { it.id == id }
            if (todo != null) {
                repository.updateTodo(todo.copy(isCompleted = !todo.isCompleted))
            }
        }
    }

    fun removeTodo(id: Int) {
        viewModelScope.launch {
            val todo = todos.value.find { it.id == id }
            if (todo != null) {
                repository.deleteTodo(todo)
            }
        }
    }

    fun editTodo(id: Int, newTitle: String, newDescription: String) {
        viewModelScope.launch {
            val todo = todos.value.find { it.id == id }
            if (todo != null) {
                repository.updateTodo(todo.copy(title = newTitle, description = newDescription))
            }
        }
    }

    fun clearAllTodos() {
        viewModelScope.launch {
            repository.clearAll()
        }
    }

    fun clearCompletedTodos() {
        viewModelScope.launch {
            repository.clearCompleted()
        }
    }
}