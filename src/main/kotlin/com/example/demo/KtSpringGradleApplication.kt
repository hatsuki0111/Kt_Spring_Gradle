package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class KtSpringGradleApplication

@Bean
fun commandLineRunner(jdbcTemplate: JdbcTemplate) = CommandLineRunner{
	jdbcTemplate.execute("""create table if not exists task(
		|id BIGINT PRIMARY KEY AUTO_INCREMENT,
		|content VARCHAR(100) NOT NULL,
		|done BOOLEAN NOT NULL DEFAULT FALSE
		|)""".trimMargin())
}


fun main(args: Array<String>) {
	runApplication<KtSpringGradleApplication>(*args)
}