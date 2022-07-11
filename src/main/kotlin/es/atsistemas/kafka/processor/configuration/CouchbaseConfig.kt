package es.atsistemas.kafka.processor.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration

@Configuration
class CouchbaseConfig : AbstractCouchbaseConfiguration() {
    override fun getConnectionString(): String {
        return "couchbase://127.0.0.1"
    }

    override fun getUserName(): String {
        return "test"
    }

    override fun getPassword(): String {
        return "secret"
    }

    override fun getBucketName(): String {
        return "test"
    }
}