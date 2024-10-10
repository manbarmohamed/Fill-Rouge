package com.fil.rouge.service;


import com.fil.rouge.dto.ClientDto;
import com.fil.rouge.exception.ClientNotFoundException;
import com.fil.rouge.mapper.ClientMapper;
import com.fil.rouge.model.Client;
import com.fil.rouge.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientDto> findAll() {
        return clientMapper.ToClientDtoList(clientRepository.findAll());
    }
    public ClientDto findById(Long id) {
        return clientMapper.ToClientDto(clientRepository.findById(id).orElseThrow());
    }
}
