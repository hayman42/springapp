package com.hayman42.springboot

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
@EnableAutoConfiguration
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}