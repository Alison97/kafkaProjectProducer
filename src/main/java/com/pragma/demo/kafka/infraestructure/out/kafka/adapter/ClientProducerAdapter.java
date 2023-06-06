package com.pragma.demo.kafka.infraestructure.out.kafka.adapter;

import com.pragma.demo.kafka.domain.model.ClientModel;
import com.pragma.demo.kafka.infraestructure.out.kafka.mapper.IClientProducerEntityMapper;
import com.pragma.demo.kafka.infraestructure.out.kafka.objects.ClientProducerEntity;
import com.pragma.demo.kafka.domain.spi.IClientProducerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
public class ClientProducerAdapter implements IClientProducerPort {

    private final KafkaTemplate<String, ClientProducerEntity> producer;
    private final IClientProducerEntityMapper clientProducerEntityMapper;


    private static final Logger logger = LoggerFactory.getLogger(ClientProducerAdapter.class);

    @Value("${create.client.kafka.topic}")
    private String topic;

    public ClientProducerAdapter(KafkaTemplate<String, ClientProducerEntity> producer, IClientProducerEntityMapper clientProducerEntityMapper) {
        this.producer = producer;
        this.clientProducerEntityMapper = clientProducerEntityMapper;
    }

    @Override
    public void save(ClientModel clientModel) {
        ClientProducerEntity client = clientProducerEntityMapper.toProducerEntity(clientModel);
        producer.send(topic,client);
        logger.info("client created : {}",client);
    }

}
