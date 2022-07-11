package es.atsistemas.kafka.processor.model

import org.springframework.data.annotation.Id

data class Client(
                @Id var id:String?,
                val nif:String,
                val firstName:String,
                val lastName:String,
                val email:String,
                var modality:String?,
                var bankAccount: String?
)