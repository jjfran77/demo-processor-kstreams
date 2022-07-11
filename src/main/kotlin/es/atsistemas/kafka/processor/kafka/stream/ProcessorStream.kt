package es.atsistemas.kafka.processor.kafka.stream

import es.atsistemas.kafka.processor.kafka.producer.KafkaClientProducer
import es.atsistemas.kafka.processor.mapper.ClientMapper
import es.atsistemas.kafka.processor.mapper.ContactMapper
import es.atsistemas.kafka.processor.model.Contact
import es.atsistemas.kafka.processor.repository.ClientRepository
import org.apache.kafka.streams.kstream.KStream
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.Tracer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

/**
 * @author Juan Francisco Guerrero (jfguerrrero@atsistemas.com)
 */
@Configuration
class ProcessorStream {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var tracer: Tracer

    @Bean
    fun contactConsumer(KafkaProcessedProducer : KafkaClientProducer, clientRepository: ClientRepository,
                        clientMapper: ClientMapper, contactMapper: ContactMapper) = Consumer<KStream<String, String>> { contactStream ->
        contactStream.foreach { key, value ->
            val logger = LoggerFactory.getLogger(javaClass)
            logger.info("Key {} - Value {}", key, value)

            var contact: Contact = contactMapper.toContact(value)

            var client = clientMapper.mapContactToClient(contact)
            client.id = "client::" + client.nif
            client.bankAccount = "es12439043940"
            client.modality = "premium"

            clientRepository.save(client)

            KafkaProcessedProducer.send(key, client)

        }
    }


}
