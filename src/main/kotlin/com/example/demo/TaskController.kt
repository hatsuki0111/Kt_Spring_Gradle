package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.client.HttpClientErrorException
import javax.validation.Valid
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.beans.factory.annotation.Autowired



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

    @PostMapping("")
    fun create(@Validated form: TaskCreateForm, bindingResult: BindingResult ): String{
        if (bindingResult.hasErrors()){
            return "tasks/new"
        }
        val content = requireNotNull(form.content)
        taskRepository.create(content)
        return "redirect:/tasks"
    }

    @GetMapping("new")
    fun new(form: TaskCreateForm): String {
        return "tasks/new"
    }

    @GetMapping("{id}/edit")
    fun edit(@PathVariable("id") id: Long, form: TaskUpdateForm): String{
        val task = taskRepository.findbyId(id) ?: throw ClassNotFoundException()
        form.content = task.content
        form.done = task.done
        return "tasks/edit"
    }

    @PostMapping("{id}")
    fun update(@PathVariable("id") id: Long, @Validated form: TaskUpdateForm, bindingResult: BindingResult): String{
        if(bindingResult.hasErrors()){
            return "tasks/edit"
        }

        val task = taskRepository.findbyId(id) ?: throw ClassNotFoundException()
        val newTask = task.copy(content = requireNotNull(form.content), done = form.done)
        taskRepository.update(newTask)
        return "redirect:/tasks"
    }
    /*
    @PostMapping("{id}")
    fun update(): String{
        return "redirect/tasks"
    }*/

}