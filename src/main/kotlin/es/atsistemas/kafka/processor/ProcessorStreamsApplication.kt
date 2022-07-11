package es.atsistemas.kafka.processor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Juan Francisco Guerrero (jfguerrrero@atsistemas.com)
 */
@SpringBootApplication
class ProcessorStreamsApplication

fun main(args: Array<String>) {
	runApplication<ProcessorStreamsApplication>(*args)
}
