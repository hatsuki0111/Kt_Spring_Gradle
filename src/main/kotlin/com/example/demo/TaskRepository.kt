package com.example.demo

interface TaskRepository {
    fun create(content: String): Task
    fun update(task: Task)
    fun findAll(): List<Task>
    fun findbyId(id: Long): Task?
}