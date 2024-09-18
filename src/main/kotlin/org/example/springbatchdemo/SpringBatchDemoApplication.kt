package org.example.springbatchdemo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBatchDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringBatchDemoApplication>(*args)
}
