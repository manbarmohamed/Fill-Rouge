package com.fil.rouge.service;

import com.fil.rouge.dto.ClientProfileUpdateDto;
import com.fil.rouge.dto.WorkerProfileUpdateDto;
import com.fil.rouge.exception.ClientNotFoundException;
import com.fil.rouge.exception.WorkerNotFoundException;
import com.fil.rouge.mapper.UserProfileMapper;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Company;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.ClientRepository;
import com.fil.rouge.repository.CompanyRepository;
import com.fil.rouge.repository.UserRepository;
import com.fil.rouge.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final WorkerRepository workerRepository;
    private final ClientRepository clientRepository;
    private final CompanyRepository companyRepository;
    private final UserProfileMapper userProfileMapper;



    public void updateProfileWorker(Long workerId, WorkerProfileUpdateDto updateDto) {
        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new WorkerNotFoundException("Worker not found"));

        userProfileMapper.updateWorkerFromDto(updateDto, worker);

        workerRepository.save(worker);
    }

    public void updateProfileClient(Long clientId, ClientProfileUpdateDto updateDto) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        userProfileMapper.updateClientFromDto(updateDto, client);

        if (updateDto.getCompany() != null) {
            if (client.getCompany() == null) {
                client.setCompany(new Company());
            }
            userProfileMapper.updateCompanyFromDto(updateDto.getCompany(), client.getCompany());
            companyRepository.save(client.getCompany());
        }

        clientRepository.save(client);
    }
}