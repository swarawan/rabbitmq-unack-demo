package com.swarawan.rabbitkiller

import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class QueueListener @Autowired constructor(
    private val publisher: QueuePublisher
) {

    @RabbitListener(queues = [QueueProperty.PROFILE_QUEUE])
    fun queueListener(message: String? = null) {
        if (message.isNullOrEmpty()) return

        val timestamp = Date().convert()
        when {
            message.contains("ganteng") -> throw AmqpRejectAndDontRequeueException("Anda tidak ganteng!!!")
            else -> println("Pesan diterima [$timestamp] : $message")
        }
    }
}