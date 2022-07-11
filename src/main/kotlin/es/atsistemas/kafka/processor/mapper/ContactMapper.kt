package es.atsistemas.kafka.processor.mapper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import es.atsistemas.kafka.processor.model.Contact
import org.springframework.stereotype.Component

/**
 * @author Juan Francisco Guerrero (jfguerrrero@atsistemas.com)
 */
@Component
class ContactMapper {

    val mapper = jacksonObjectMapper()

    fun toJson(contact: Contact) : String {
        return mapper.writeValueAsString(contact)
    }

    fun toContact(contact: String): Contact {
        return mapper.readValue<Contact>(contact, Contact::class.java)
    }

}