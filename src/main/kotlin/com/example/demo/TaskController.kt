package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//./gradlew bootRun

@Controller
@RequestMapping("tasks")
class TaskController (private val taskRepository: TaskRepository){
    @GetMapping("")
    fun index(model: Model): String{
        val tasks = taskRepository.findAll()
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }
}