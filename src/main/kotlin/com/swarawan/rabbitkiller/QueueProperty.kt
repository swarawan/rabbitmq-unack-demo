package com.swarawan.rabbitkiller

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class QueueProperty {

    companion object {
        const val PROFILE_QUEUE = "app.profile"
        const val PROFILE_DLX_QUEUE = "app.profile.dlx"

        const val ARGS_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange"
        const val ARGS_DEAD_LETTER_ROUTING = "x-dead-letter-routing-key"
    }

    @Bean
    fun profileQueue(): Queue = createQueue(
        name = PROFILE_QUEUE,
        arguments = mapOf(
            ARGS_DEAD_LETTER_EXCHANGE to "",
            ARGS_DEAD_LETTER_ROUTING to PROFILE_DLX_QUEUE
        )
    )

    @Bean
    fun profileQueueDlx(): Queue = createQueue(PROFILE_DLX_QUEUE)

    private fun createQueue(
        name: String,
        durable: Boolean = true,
        exclusive: Boolean = false,
        autoDelete: Boolean = false,
        arguments: Map<String, String>? = null
    ) = Queue(name, durable, exclusive, autoDelete).apply {
        arguments?.forEach { (key, value) ->
            addArgument(key, value)
        }
    }
}