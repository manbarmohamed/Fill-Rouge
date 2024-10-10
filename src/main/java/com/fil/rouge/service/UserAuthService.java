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
}
