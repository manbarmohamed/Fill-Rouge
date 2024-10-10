//package com.fil.rouge;
//
//import com.fil.rouge.dto.ApplicationDto;
//import com.fil.rouge.dto.ApplicationDisplayDto;
//import com.fil.rouge.emuns.ApplicationStatus;
//import com.fil.rouge.exception.ApplicationNotFoundException;
//import com.fil.rouge.exception.TaskNotFoundException;
//import com.fil.rouge.exception.WorkerNotFoundException;
//import com.fil.rouge.mapper.ApplicationMapper;
//import com.fil.rouge.model.Application;
//import com.fil.rouge.model.Task;
//import com.fil.rouge.model.Worker;
//import com.fil.rouge.repository.ApplicationRepository;
//import com.fil.rouge.repository.TaskRepository;
//import com.fil.rouge.repository.WorkerRepository;
//import com.fil.rouge.service.ApplicationService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ApplicationServiceTest {
//
//    @Mock
//    private ApplicationRepository applicationRepository;
//
//    @Mock
//    private TaskRepository taskRepository;
//
//    @Mock
//    private WorkerRepository workerRepository;
//
//    @Mock
//    private ApplicationMapper applicationMapper;
//
//    @InjectMocks
//    private ApplicationService applicationService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSubmitApplication_Success() {
//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setTaskId(1L);
//        applicationDto.setWorkerId(1L);
//
//        Task task = new Task();
//        Worker worker = new Worker();
//        Application application = new Application();
//
//        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
//        when(workerRepository.findById(1L)).thenReturn(Optional.of(worker));
//        when(applicationMapper.toEntity(applicationDto)).thenReturn(application);
//        when(applicationRepository.save(application)).thenReturn(application);
//        when(applicationMapper.toDto(application)).thenReturn(applicationDto);
//
//        ApplicationDto result = applicationService.submitApplication(applicationDto);
//
//        assertNotNull(result);
//        assertEquals(applicationDto, result);
//        assertEquals(ApplicationStatus.PENDING, application.getStatus());
//        verify(applicationRepository).save(application);
//    }
//
//    @Test
//    void testSubmitApplication_TaskNotFound() {
//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setTaskId(1L);
//
//        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(TaskNotFoundException.class, () -> applicationService.submitApplication(applicationDto));
//    }
//
//    @Test
//    void testSubmitApplication_WorkerNotFound() {
//        ApplicationDto applicationDto = new ApplicationDto();
//        applicationDto.setTaskId(1L);
//        applicationDto.setWorkerId(1L);
//
//        Task task = new Task();
//
//        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
//        when(workerRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(WorkerNotFoundException.class, () -> applicationService.submitApplication(applicationDto));
//    }
//
//    @Test
//    void testAcceptApplication_Success() {
//        Application application = new Application();
//        application.setId(1L);
//        application.setStatus(ApplicationStatus.PENDING);
//
//        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
//
//        String result = applicationService.acceptApplication(1L);
//
//        assertEquals("Application accepted successfully", result);
//        assertEquals(ApplicationStatus.ACCEPTED, application.getStatus());
//        verify(applicationRepository).save(application);
//    }
//
//    @Test
//    void testAcceptApplication_ApplicationNotFound() {
//        when(applicationRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ApplicationNotFoundException.class, () -> applicationService.acceptApplication(1L));
//    }
//
//    @Test
//    void testRejectApplication_Success() {
//        Application application = new Application();
//        application.setId(1L);
//        application.setStatus(ApplicationStatus.PENDING);
//
//        when(applicationRepository.findById(1L)).thenReturn(Optional.of(application));
//
//        String result = applicationService.rejectApplication(1L);
//
//        assertEquals("Application rejected successfully", result);
//        assertEquals(ApplicationStatus.REJECTED, application.getStatus());
//        verify(applicationRepository).save(application);
//    }
//
//    @Test
//    void testGetApplications() {
//        List<Application> applications = List.of(new Application(), new Application());
//        List<ApplicationDto> applicationDtos = List.of(new ApplicationDto(), new ApplicationDto());
//
//        when(applicationRepository.findAll()).thenReturn(applications);
//        when(applicationMapper.toDto(applications)).thenReturn(applicationDtos);
//
//        List<ApplicationDto> result = applicationService.getApplications();
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(applicationRepository).findAll();
//    }
//}
