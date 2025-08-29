package com.example.todoapp.data.repository

import com.example.todoapp.data.TodoDao
import com.example.todoapp.data.local.entity.toDomain
import com.example.todoapp.data.local.entity.toEntity
import com.example.todoapp.domain.model.TodoItem
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {
    override fun getAllTodos(): Flow<List<TodoItem>> =
        dao.getAllTodos().map { list -> list.map { it.toDomain() } }

    override suspend fun insertTodo(todo: TodoItem) {
        dao.insertTodo(todo.toEntity())
    }

    override suspend fun updateTodo(todo: TodoItem) {
        dao.updateTodo(todo.toEntity())
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        dao.deleteTodo(todo.toEntity())
    }

    override suspend fun clearAll() {
        dao.clearAll()
    }

    override suspend fun clearCompleted() {
        dao.clearCompleted()
    }
}

