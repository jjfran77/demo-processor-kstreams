# demo-processor-streams

1. Start a local Kafka instance
2. Send a message to the Kafka topic using the Rest controller: `curl "localhost:8080/sendMessage?text=hello"`
3. The message is received and processed in the KStream located in `TextKStreamConfiguration`
4. The message is sended to output kafka topic
