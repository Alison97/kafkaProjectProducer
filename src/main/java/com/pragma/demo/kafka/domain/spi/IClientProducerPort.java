package com.pragma.demo.kafka.domain.spi;

import com.pragma.demo.kafka.domain.model.ClientModel;
import com.pragma.demo.kafka.infraestructure.out.kafka.objects.ClientProducerEntity;

public interface IClientProducerPort {

    void save(ClientModel clientModel);

}
