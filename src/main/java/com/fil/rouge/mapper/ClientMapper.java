package com.fil.rouge.mapper;


import com.fil.rouge.dto.ClientDto;
import com.fil.rouge.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface ClientMapper {

    Client ToClient(ClientDto clientDto);

    ClientDto ToClientDto(Client client);

    List<Client> ToClientList(List<ClientDto> clientDtoList);
    List<ClientDto> ToClientDtoList(List<Client> clientList);
}
