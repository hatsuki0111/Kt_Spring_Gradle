package com.example.demo.Hello

import com.example.demo.Greeter.Greeter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

//8080/hello?name=Spring

@RestController
class HelloController(private val greeter: Greeter){

    @GetMapping("hello")
    fun hello(@RequestParam("name") name: String): String  = greeter.hello(name)
}