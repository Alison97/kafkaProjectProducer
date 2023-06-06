package com.pragma.demo.kafka.infraestructure.out.kafka.mapper;

import com.pragma.demo.kafka.domain.model.ClientModel;
import com.pragma.demo.kafka.infraestructure.out.kafka.objects.ClientProducerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IClientProducerEntityMapper {

    ClientProducerEntity toProducerEntity(ClientModel client);
}
