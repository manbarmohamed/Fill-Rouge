//package com.fil.rouge;
//
//
//import com.fil.rouge.dto.PaymentCreateDto;
//import com.fil.rouge.dto.PaymentDisplayDto;
//import com.fil.rouge.emuns.PaymentStatus;
//import com.fil.rouge.exception.PaymentNotFoundException;
//import com.fil.rouge.mapper.PaymentMapper;
//import com.fil.rouge.model.Payment;
//import com.fil.rouge.model.Worker;
//import com.fil.rouge.repository.PaymentRepository;
//import com.fil.rouge.service.PaymentService;
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
//class PaymentServiceTest {
//
//    @Mock
//    private PaymentRepository paymentRepository;
//
//    @Mock
//    private PaymentMapper paymentMapper;
//
//    @InjectMocks
//    private PaymentService paymentService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreatePayment_Success() {
//        PaymentCreateDto paymentCreateDto = new PaymentCreateDto();
//        Payment payment = new Payment();
//        payment.setStatus(PaymentStatus.PENDING);
//        Payment savedPayment = new Payment();
//        PaymentDisplayDto paymentDisplayDto = new PaymentDisplayDto();
//
//        when(paymentMapper.toEntity(paymentCreateDto)).thenReturn(payment);
//        when(paymentRepository.save(payment)).thenReturn(savedPayment);
//        when(paymentMapper.toDisplayDto(savedPayment)).thenReturn(paymentDisplayDto);
//
//        PaymentDisplayDto result = paymentService.createPayment(paymentCreateDto);
//
//        assertNotNull(result);
//        verify(paymentRepository).save(payment);
//        assertEquals(paymentDisplayDto, result);
//    }
//
//    @Test
//    void testGetPayment_Success() {
//        Payment payment = new Payment();
//        PaymentDisplayDto paymentDisplayDto = new PaymentDisplayDto();
//
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
//        when(paymentMapper.toDisplayDto(payment)).thenReturn(paymentDisplayDto);
//
//        PaymentDisplayDto result = paymentService.getPayment(1L);
//
//        assertNotNull(result);
//        assertEquals(paymentDisplayDto, result);
//        verify(paymentRepository).findById(1L);
//    }
//
//    @Test
//    void testGetPayment_NotFound() {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.getPayment(1L));
//    }
//
//    @Test
//    void testUpdatePayment_Success() {
//        PaymentCreateDto paymentCreateDto = new PaymentCreateDto();
//        Payment existingPayment = new Payment();
//        Payment updatedPayment = new Payment();
//        PaymentDisplayDto paymentDisplayDto = new PaymentDisplayDto();
//
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(existingPayment));
//        doNothing().when(paymentMapper).partialUpdate(paymentCreateDto, existingPayment);
//        when(paymentRepository.save(existingPayment)).thenReturn(updatedPayment);
//        when(paymentMapper.toDisplayDto(updatedPayment)).thenReturn(paymentDisplayDto);
//
//        PaymentDisplayDto result = paymentService.updatePayment(1L, paymentCreateDto);
//
//        assertNotNull(result);
//        assertEquals(paymentDisplayDto, result);
//        verify(paymentRepository).save(existingPayment);
//    }
//
//    @Test
//    void testUpdatePayment_NotFound() {
//        PaymentCreateDto paymentCreateDto = new PaymentCreateDto();
//
//        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.updatePayment(1L, paymentCreateDto));
//    }
//
//    @Test
//    void testDeletePayment_Success() {
//        when(paymentRepository.existsById(1L)).thenReturn(true);
//
//        paymentService.deletePayment(1L);
//
//        verify(paymentRepository).deleteById(1L);
//    }
//
//    @Test
//    void testDeletePayment_NotFound() {
//        when(paymentRepository.existsById(1L)).thenReturn(false);
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.deletePayment(1L));
//    }
//
//    @Test
//    void testProcessPayment_Success() {
//        Payment payment = new Payment();
//        payment.setAmount(100.0);
//        Worker worker = new Worker();
//        worker.setBalance(50.0);
//        payment.setWorker(worker);
//        Payment updatedPayment = new Payment();
//        PaymentDisplayDto paymentDisplayDto = new PaymentDisplayDto();
//
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
//        when(paymentRepository.save(payment)).thenReturn(updatedPayment);
//        when(paymentMapper.toDisplayDto(updatedPayment)).thenReturn(paymentDisplayDto);
//
//        PaymentDisplayDto result = paymentService.processPayment(1L);
//
//        assertNotNull(result);
//        assertEquals(150.0, worker.getBalance()); // Check the worker's balance
//        assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
//        verify(paymentRepository).save(payment);
//    }
//
//    @Test
//    void testProcessPayment_NotFound() {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.processPayment(1L));
//    }
//
//    @Test
//    void testRefundPayment_Success() {
//        Payment payment = new Payment();
//        payment.setStatus(PaymentStatus.PENDING);
//        Payment updatedPayment = new Payment();
//        PaymentDisplayDto paymentDisplayDto = new PaymentDisplayDto();
//
//        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
//        when(paymentRepository.save(payment)).thenReturn(updatedPayment);
//        when(paymentMapper.toDisplayDto(updatedPayment)).thenReturn(paymentDisplayDto);
//
//        PaymentDisplayDto result = paymentService.refundPayment(1L);
//
//        assertNotNull(result);
//        assertEquals(PaymentStatus.REFUNDED, payment.getStatus());
//        verify(paymentRepository).save(payment);
//    }
//
//    @Test
//    void testRefundPayment_NotFound() {
//        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(PaymentNotFoundException.class, () -> paymentService.refundPayment(1L));
//    }
//
//    @Test
//    void testGetAllPayments() {
//        List<Payment> payments = List.of(new Payment(), new Payment());
//        List<PaymentDisplayDto> paymentDtos = List.of(new PaymentDisplayDto(), new PaymentDisplayDto());
//
//        when(paymentRepository.findAll()).thenReturn(payments);
//        when(paymentMapper.toDisplayDto(payments)).thenReturn(paymentDtos);
//
//        List<PaymentDisplayDto> result = paymentService.getAllPayments();
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(paymentRepository).findAll();
//    }
//
//    @Test
//    void testGetPaymentsByClient() {
//        List<Payment> payments = List.of(new Payment(), new Payment());
//        List<PaymentDisplayDto> paymentDtos = List.of(new PaymentDisplayDto(), new PaymentDisplayDto());
//
//        when(paymentRepository.findByClientId(1L)).thenReturn(payments);
//        when(paymentMapper.toDisplayDto(payments)).thenReturn(paymentDtos);
//
//        List<PaymentDisplayDto> result = paymentService.getPaymentsByClient(1L);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(paymentRepository).findByClientId(1L);
//    }
//
//    @Test
//    void testGetPaymentsByWorker() {
//        List<Payment> payments = List.of(new Payment(), new Payment());
//        List<PaymentDisplayDto> paymentDtos = List.of(new PaymentDisplayDto(), new PaymentDisplayDto());
//
//        when(paymentRepository.findByWorkerId(1L)).thenReturn(payments);
//        when(paymentMapper.toDisplayDto(payments)).thenReturn(paymentDtos);
//
//        List<PaymentDisplayDto> result = paymentService.getPaymentsByWorker(1L);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(paymentRepository).findByWorkerId(1L);
//    }
//}
