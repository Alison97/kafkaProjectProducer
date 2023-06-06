package com.pragma.demo.kafka.domain.usecase;

import com.pragma.demo.kafka.domain.api.IClientServicePort;
import com.pragma.demo.kafka.domain.model.ClientModel;
import com.pragma.demo.kafka.domain.spi.IClientProducerPort;

public class ClientUseCase implements IClientServicePort {

    private final IClientProducerPort clientProducerPort;

    public ClientUseCase(IClientProducerPort clientProducerPort) {
        this.clientProducerPort = clientProducerPort;
    }


    @Override
    public void saveClient(ClientModel clientModel) {
        clientProducerPort.save(clientModel);
    }
}
