package com.github.healthverse

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthverseApplication

fun main(args: Array<String>) {
    runApplication<HealthverseApplication>(*args)
}
