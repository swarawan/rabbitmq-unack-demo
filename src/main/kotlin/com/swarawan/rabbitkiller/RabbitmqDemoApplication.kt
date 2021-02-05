package com.swarawan.rabbitkiller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.text.SimpleDateFormat
import java.util.*

@SpringBootApplication
@EnableAutoConfiguration
class RabbitmqDemoApplication @Autowired constructor(
    private val publisher: QueuePublisher
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        publisher.start()
    }
}

fun main(args: Array<String>) {
    runApplication<RabbitmqDemoApplication>(*args)
}

fun Date.convert(pattern: String = "dd-MM-yyyy hh:mm:ss"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}

