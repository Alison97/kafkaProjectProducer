package com.pragma.demo.kafka.infraestructure.out.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    private final ObjectMapper objectMapper;

    public KafkaProducerConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static Map<String, Object> getConfig(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return config;
    }

    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate(ProducerFactory<String, T> userFactory) {
        return new KafkaTemplate<>(userFactory);
    }

    @Bean
    public <T> ProducerFactory<String, T> producerFactory(KafkaProperties kafkaProperties) {
        JsonSerializer<T> valueSerializer = new JsonSerializer<>(objectMapper);
        return new DefaultKafkaProducerFactory<>(
                getConfig(kafkaProperties),
                new StringSerializer(),
                valueSerializer.noTypeInfo()
        );
    }


}
