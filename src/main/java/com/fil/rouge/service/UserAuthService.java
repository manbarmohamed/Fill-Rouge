package com.fil.rouge.service;

import com.fil.rouge.dto.JwtResponse;
import com.fil.rouge.dto.LoginDto;
import com.fil.rouge.dto.SignupDto;
import com.fil.rouge.mapper.UserMapper;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.User;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.ClientRepository;
import com.fil.rouge.repository.UserRepository;
import com.fil.rouge.repository.WorkerRepository;
import com.fil.rouge.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void signup(SignupDto signupDto) {
        if (signupDto.getCompanyName() != null) { // Client
            Client client = userMapper.signupDtoToClient(signupDto);
            client.setPassword(encoder.encode(signupDto.getPassword()));
            clientRepository.save(client);
        } else { // Worker
            Worker worker = userMapper.signupDtoToWorker(signupDto);
            worker.setPassword(encoder.encode(signupDto.getPassword()));
            workerRepository.save(worker);
        }
    }

//    public JwtResponse login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String jwtToken = jwtUtils.generateToken(userDetails);
//
//        User user = (User) userDetailsService.loadUserByUsername(loginDto.getUsername());
//        String role = user.getClass().getSimpleName().toUpperCase();
//        System.out.println(role +"rooooooooooooooole");
//        return new JwtResponse(jwtToken, user.getId(), user.getUsername(), role);
//    }


    public JwtResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user =userRepository.findByUsername(loginDto.getUsername());
           String token = jwtUtils.generateToken((UserDetails) user);
            String role = user.getClass().getSimpleName().toUpperCase();

            return  new JwtResponse().builder()
                   .token(token)
                    .username(user.getUsername())
                    .role(role)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }
}

