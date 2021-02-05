package com.swarawan.rabbitkiller

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueuePublisher @Autowired constructor(
    private val rabbitTemplate: RabbitTemplate
) {

    private val scanner = Scanner(System.`in`)

    fun start() {
        saySomething()
    }

    private fun saySomething() {
        println("")
        println("")
        print("Masukkan sebuah pesan: ")
        val message = scanner.nextLine()

        val timestamp = Date().convert()
        println("Pesan berhasil dikirim [$timestamp] : $message")
        rabbitTemplate.convertAndSend(QueueProperty.PROFILE_QUEUE, message)
    }
}