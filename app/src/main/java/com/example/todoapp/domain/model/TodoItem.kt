package com.example.todoapp.domain.model

data class TodoItem(
    val id: Int,
    val title: String,
    val description: String,
    val isComplete: Boolean
)
