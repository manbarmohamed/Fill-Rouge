//package com.fil.rouge;
//
//import com.fil.rouge.dto.SignupDto;
//import com.fil.rouge.emuns.Role;
//import com.fil.rouge.mapper.UserMapper;
//import com.fil.rouge.model.Admin;
//import com.fil.rouge.model.Client;
//import com.fil.rouge.model.Company;
//import com.fil.rouge.model.Worker;
//import com.fil.rouge.repository.AdminRepository;
//import com.fil.rouge.repository.ClientRepository;
//import com.fil.rouge.repository.CompanyRepository;
//import com.fil.rouge.repository.WorkerRepository;
//import com.fil.rouge.service.UserAuthService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.mockito.Mockito.*;
//
//public class UserAuthServiceTest {
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Mock
//    private WorkerRepository workerRepository;
//
//    @Mock
//    private AdminRepository adminRepository;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @Mock
//    private BCryptPasswordEncoder encoder;
//
//    @InjectMocks
//    private UserAuthService userAuthService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSignup_Client() {
//        // Given
//        SignupDto signupDto = new SignupDto();
//        signupDto.setRole(Role.CLIENT);
//        signupDto.setPassword("clientPassword");
//        Company company = new Company();
//
//        Client client = new Client();
//        client.setCompany(company);
//
//        when(userMapper.clientSignupDtoToClient(signupDto)).thenReturn(client);
//        when(encoder.encode("clientPassword")).thenReturn("encodedPassword");
//
//        // When
//        userAuthService.signup(signupDto);
//
//        // Then
//        verify(companyRepository).save(company);
//        verify(clientRepository).save(client);
//        verify(client).setPassword("encodedPassword");
//    }
//
//    @Test
//    public void testSignup_Worker() {
//        // Given
//        SignupDto signupDto = new SignupDto();
//        signupDto.setRole(Role.WORKER);
//        signupDto.setPassword("workerPassword");
//
//        Worker worker = new Worker();
//        when(userMapper.workerSignupDtoToWorker(signupDto)).thenReturn(worker);
//        when(encoder.encode("workerPassword")).thenReturn("encodedPassword");
//
//        // When
//        userAuthService.signup(signupDto);
//
//        // Then
//        verify(workerRepository).save(worker);
//        verify(worker).setPassword("encodedPassword");
//    }
//
//    @Test
//    public void testSignup_Admin() {
//        // Given
//        SignupDto signupDto = new SignupDto();
//        signupDto.setRole(Role.ADMIN);
//        signupDto.setPassword("adminPassword");
//
//        Admin admin = new Admin();
//        when(userMapper.adminSignupDtoToAdmin(signupDto)).thenReturn(admin);
//        when(encoder.encode("adminPassword")).thenReturn("encodedPassword");
//
//        // When
//        userAuthService.signup(signupDto);
//
//        // Then
//        verify(adminRepository).save(admin);
//        verify(admin).setPassword("encodedPassword");
//    }
//}
