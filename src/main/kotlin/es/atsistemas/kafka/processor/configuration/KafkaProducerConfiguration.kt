package es.atsistemas.kafka.processor.configuration

import org.apache.kafka.streams.kstream.KStream
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanFactory
import org.springframework.cloud.sleuth.instrument.messaging.MessagingSleuthOperators
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * @author Juan Francisco Guerrero (jfguerrrero@atsistemas.com)
 */
@Configuration
class KafkaProducerConfiguration(private val beanFactory: BeanFactory) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun contactProducerSink(): Sinks.Many<Message<String>> = Sinks.many().unicast().onBackpressureBuffer()

    @Bean
    fun clientProducerSink(): Sinks.Many<Message<String>> = Sinks.many().unicast().onBackpressureBuffer()

    @Bean
    fun contactProducer(): Supplier<Flux<Message<String>>> = Supplier {
        contactProducerSink().asFlux().map {
            val msg = MessagingSleuthOperators.handleOutputMessage(beanFactory, MessagingSleuthOperators.forInputMessage(beanFactory, it))
            logger.info("Produced message for text{}", msg)
            msg
        }
    }

    @Bean
    fun clientProducer(): Supplier<Flux<Message<String>>> = Supplier {
        clientProducerSink().asFlux().map {
            val msg = MessagingSleuthOperators.handleOutputMessage(beanFactory, MessagingSleuthOperators.forInputMessage(beanFactory, it))
            logger.info("Produced message for text{}", msg)
            msg
        }
    }







}
