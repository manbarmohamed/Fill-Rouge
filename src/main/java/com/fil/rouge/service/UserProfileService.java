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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final WorkerRepository workerRepository;
    private final ClientRepository clientRepository;
    private final CompanyRepository companyRepository;
    private final UserProfileMapper userProfileMapper;

    @Transactional
    public void updateProfileWorker( WorkerProfileUpdateDto updateDto, MultipartFile profileImage) throws IOException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());


        userProfileMapper.updateWorkerFromDto(updateDto, worker);

        if (profileImage != null && !profileImage.isEmpty()) {
            worker.setProfileImage(profileImage.getBytes());
        }

        workerRepository.save(worker);
    }

    @Transactional
    public void updateProfileClient( ClientProfileUpdateDto updateDto, MultipartFile profileImage, MultipartFile companyImage) throws IOException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        userProfileMapper.updateClientFromDto(updateDto, client);

        if (profileImage != null && !profileImage.isEmpty()) {
            client.setProfileImage(profileImage.getBytes());
        }

        if (updateDto.getCompany() != null) {
            if (client.getCompany() == null) {
                client.setCompany(new Company());
            }
            userProfileMapper.updateCompanyFromDto(updateDto.getCompany(), client.getCompany());

            if (companyImage != null && !companyImage.isEmpty()) {
                client.getCompany().setCompanyImage(companyImage.getBytes());
            }

            companyRepository.save(client.getCompany());
        }

        clientRepository.save(client);
    }

//    public void updateProfileWorker(Long workerId, WorkerProfileUpdateDto updateDto) {
//        Worker worker = workerRepository.findById(workerId)
//                .orElseThrow(() -> new WorkerNotFoundException("Worker not found"));
//
//        userProfileMapper.updateWorkerFromDto(updateDto, worker);
//
//        workerRepository.save(worker);
//    }
//
//    public void updateProfileClient(Long clientId, ClientProfileUpdateDto updateDto) {
//        Client client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
//
//        userProfileMapper.updateClientFromDto(updateDto, client);
//
//        if (updateDto.getCompany() != null) {
//            if (client.getCompany() == null) {
//                client.setCompany(new Company());
//            }
//            userProfileMapper.updateCompanyFromDto(updateDto.getCompany(), client.getCompany());
//            companyRepository.save(client.getCompany());
//        }
//
//        clientRepository.save(client);
//    }

}