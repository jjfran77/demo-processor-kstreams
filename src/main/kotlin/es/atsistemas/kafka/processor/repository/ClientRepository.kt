package es.atsistemas.kafka.processor.repository

import es.atsistemas.kafka.processor.model.Client
import org.springframework.data.couchbase.repository.CouchbaseRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : CouchbaseRepository<Client?, String?> {
    fun findByEmailLike(email: String?): List<Client?>?
}