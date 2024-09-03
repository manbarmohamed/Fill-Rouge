package com.fil.rouge.mapper;

import com.fil.rouge.dto.PaymentCreateDto;
import com.fil.rouge.dto.PaymentDisplayDto;
import com.fil.rouge.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "worker.name", target = "workerName")
    @Mapping(source = "status", target = "status")
    PaymentDisplayDto toDisplayDto(Payment payment);

    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "worker.name", target = "workerName")
    @Mapping(source = "status", target = "status")
    List<PaymentDisplayDto> toDisplayDto(List<Payment> payment);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "workerId", target = "worker.id")
    Payment toEntity(PaymentCreateDto paymentCreateDto);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "workerId", target = "worker.id")
    void partialUpdate(PaymentCreateDto paymentCreateDto, @MappingTarget Payment payment);
}
