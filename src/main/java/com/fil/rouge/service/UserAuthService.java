package com.fil.rouge.service;

import com.fil.rouge.dto.*;
import com.fil.rouge.emuns.Role;
import com.fil.rouge.exception.InvalidPasswordException;
import com.fil.rouge.exception.UserNotFoundException;
import com.fil.rouge.mapper.UserMapper;
import com.fil.rouge.model.Admin;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.User;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.*;
import com.fil.rouge.security.JwtUtils;
import jakarta.persistence.DiscriminatorValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserAuthService {


    private final ClientRepository clientRepository;

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private final AdminRepository adminRepository;

    private final WorkerRepository workerRepository;


    private final UserMapper userMapper;


    private final JwtUtils jwtUtils;


    private final AuthenticationManager authenticationManager;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public void signup(SignupDto signupDto) {
        if (signupDto.getRole() == Role.CLIENT) {
            SignupDto clientSignupDto =  signupDto;
            Client client = userMapper.clientSignupDtoToClient(clientSignupDto);
            client.setPassword(encoder.encode(clientSignupDto.getPassword()));
            if (client.getCompany() != null) {
                companyRepository.save(client.getCompany());
            }
            clientRepository.save(client);
        } else if (signupDto.getRole() == Role.WORKER) {
            SignupDto workerSignupDto =signupDto;
            Worker worker = userMapper.workerSignupDtoToWorker(workerSignupDto);
            worker.setPassword(encoder.encode(workerSignupDto.getPassword()));
            workerRepository.save(worker);

        } else if (signupDto.getRole() == Role.ADMIN) {
            SignupDto adminSignupDto =signupDto;
            Admin admin = userMapper.adminSignupDtoToAdmin(adminSignupDto);
            admin.setPassword(encoder.encode(adminSignupDto.getPassword()));
            adminRepository.save(admin);

        }
    }



//    public void signup(SignupDto signupDto) {
//        if (signupDto.getCompanyName() != null) {
//            Client client = userMapper.signupDtoToClient(signupDto);
//            client.setPassword(encoder.encode(signupDto.getPassword()));
//            clientRepository.save(client);
//        } else if (signupDto.getSkill() !=null){
//            Worker worker = userMapper.signupDtoToWorker(signupDto);
//            worker.setPassword(encoder.encode(signupDto.getPassword()));
//            workerRepository.save(worker);
//        }
//        else {
//            Admin admin = userMapper.signupDtoToAdmin(signupDto);
//            admin.setPassword(encoder.encode(signupDto.getPassword()));
//            adminRepository.save(admin);
//        }
//    }
//    public JwtResponse login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
//        );
//
//        if (authentication.isAuthenticated()) {
//            User user =userRepository.findByUsername(loginDto.getUsername());
//           String token = jwtUtils.generateToken((UserDetails) user);
//            String role = user.getClass().getSimpleName().toUpperCase();
//
//            return  new JwtResponse().builder()
//                   .token(token)
//                    .username(user.getUsername())
//                    .role(role)
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("Invalid user request.");
//        }
//    }


    public JwtResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(loginDto.getUsername());
            String token = jwtUtils.generateToken(user, user.getRole());

            return JwtResponse.builder()
                    .token(token)
                    .username(user.getUsername())
                    .role(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }



//    public User updateProfile(Long userId, UpdateProfileDto updateProfileDto) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//        if (updateProfileDto.getOldPassword() != null && updateProfileDto.getNewPassword() != null) {
//            if (!encoder.matches(updateProfileDto.getOldPassword(), user.getPassword())) {
//                throw new InvalidPasswordException("Old password is incorrect");
//            }
//            user.setPassword(encoder.encode(updateProfileDto.getNewPassword()));
//        }
//
//        userMapper.updateUserFromDto(updateProfileDto, user);
//
//        if (user instanceof Client) {
//            Client client = (Client) user;
//            userMapper.updateClientFromDto(updateProfileDto, client);
//            clientRepository.save(client);
//        } else if (user instanceof Worker) {
//            Worker worker = (Worker) user;
//            userMapper.updateWorkerFromDto(updateProfileDto, worker);
//            workerRepository.save(worker);
//        }
//
//        return userRepository.save(user);
//    }

}

