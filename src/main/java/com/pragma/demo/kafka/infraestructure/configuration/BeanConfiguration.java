package com.pragma.demo.kafka.infraestructure.configuration;

import com.pragma.demo.kafka.domain.api.IClientServicePort;
import com.pragma.demo.kafka.domain.spi.IClientProducerPort;
import com.pragma.demo.kafka.domain.usecase.ClientUseCase;
import com.pragma.demo.kafka.infraestructure.out.kafka.adapter.ClientProducerAdapter;
import com.pragma.demo.kafka.infraestructure.out.kafka.mapper.IClientProducerEntityMapper;
import com.pragma.demo.kafka.infraestructure.out.kafka.objects.ClientProducerEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class BeanConfiguration {

    private final KafkaTemplate<String, ClientProducerEntity> clientProducer;
    private final IClientProducerEntityMapper clientProducerEntityMapper;

    public BeanConfiguration(KafkaTemplate<String, ClientProducerEntity> clientProducer, IClientProducerEntityMapper clientProducerEntityMapper) {
        this.clientProducer = clientProducer;
        this.clientProducerEntityMapper = clientProducerEntityMapper;
    }

    @Bean
    public IClientProducerPort usuarioProducerPort() {
        return new ClientProducerAdapter(clientProducer,clientProducerEntityMapper);
    }


    @Bean
    public IClientServicePort clientServicePort() {
        return new ClientUseCase(usuarioProducerPort());
    }


}
