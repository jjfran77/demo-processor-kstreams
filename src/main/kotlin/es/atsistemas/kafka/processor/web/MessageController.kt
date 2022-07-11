package es.atsistemas.kafka.processor.web

import es.atsistemas.kafka.processor.kafka.producer.KafkaContactProducer
import es.atsistemas.kafka.processor.model.Contact
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

/**
 * @author Juan Francisco Guerrero (jfguerrrero@atsistemas.com)
 */
@RestController
class MessageController(private val kafkaContactProducer: KafkaContactProducer) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(path = ["/contact"], consumes = ["application/json"])
    fun send(@RequestBody contact: Contact): Mono<Void> {
        logger.info("send() {}", contact)
        return kafkaContactProducer.send(contact)
    }

}
