package es.atsistemas.kafka.processor.kafka.producer

import es.atsistemas.kafka.processor.mapper.ClientMapper
import es.atsistemas.kafka.processor.model.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.TraceContext
import org.springframework.cloud.sleuth.Tracer
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import java.time.OffsetDateTime


/**
 * @author Juan Francisco Guerrero (jfguerrrero@atsistemas.com)
 */
@Component
class KafkaClientProducer(private val clientProducerSink: Sinks.Many<Message<String>>, private val clientMapper: ClientMapper) {

    @Autowired
    lateinit var tracer: Tracer

    fun send(key: String, client: Client): Mono<Void> {

        tracer.startScopedSpan("")

        val dateTime = OffsetDateTime.now().toInstant().toEpochMilli()

        val value = clientMapper.toJson(client)

        val message = MessageBuilder.withPayload(value)
            .setHeader(KafkaHeaders.MESSAGE_KEY, key)
            .setHeader(KafkaHeaders.TIMESTAMP, dateTime)
            .build()

        clientProducerSink.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST)
        return Mono.empty()
    }


}