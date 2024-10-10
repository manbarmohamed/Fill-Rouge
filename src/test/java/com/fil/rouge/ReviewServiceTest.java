//package com.fil.rouge;
//
//
//import com.fil.rouge.dto.ReviewDto;
//import com.fil.rouge.exception.ClientNotFoundException;
//import com.fil.rouge.exception.ReviewNotFoundException;
//import com.fil.rouge.exception.WorkerNotFoundException;
//import com.fil.rouge.mapper.ReviewMapper;
//import com.fil.rouge.model.Client;
//import com.fil.rouge.model.Review;
//import com.fil.rouge.model.Worker;
//import com.fil.rouge.repository.ClientRepository;
//import com.fil.rouge.repository.ReviewRepository;
//import com.fil.rouge.repository.WorkerRepository;
//import com.fil.rouge.service.ReviewService;
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
//class ReviewServiceTest {
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @Mock
//    private ClientRepository clientRepository;
//
//    @Mock
//    private WorkerRepository workerRepository;
//
//    @Mock
//    private ReviewMapper reviewMapper;
//
//    @InjectMocks
//    private ReviewService reviewService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateReview_Success() {
//        ReviewDto reviewDto = new ReviewDto();
//        reviewDto.setClientId(1L);
//        reviewDto.setWorkerId(2L);
//
//        Client client = new Client();
//        Worker worker = new Worker();
//        Review review = new Review();
//        Review savedReview = new Review();
//        ReviewDto savedReviewDto = new ReviewDto();
//
//        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
//        when(workerRepository.findById(2L)).thenReturn(Optional.of(worker));
//        when(reviewMapper.toEntity(reviewDto)).thenReturn(review);
//        when(reviewRepository.save(review)).thenReturn(savedReview);
//        when(reviewMapper.toDto(savedReview)).thenReturn(savedReviewDto);
//
//        ReviewDto result = reviewService.createReview(reviewDto);
//
//        assertNotNull(result);
//        verify(clientRepository).findById(1L);
//        verify(workerRepository).findById(2L);
//        verify(reviewRepository).save(review);
//        assertEquals(savedReviewDto, result);
//    }
//
//    @Test
//    void testCreateReview_ClientNotFound() {
//        ReviewDto reviewDto = new ReviewDto();
//        reviewDto.setClientId(1L);
//        reviewDto.setWorkerId(2L);
//
//        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ClientNotFoundException.class, () -> reviewService.createReview(reviewDto));
//    }
//
//    @Test
//    void testCreateReview_WorkerNotFound() {
//        ReviewDto reviewDto = new ReviewDto();
//        reviewDto.setClientId(1L);
//        reviewDto.setWorkerId(2L);
//
//        Client client = new Client();
//
//        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
//        when(workerRepository.findById(2L)).thenReturn(Optional.empty());
//
//        assertThrows(WorkerNotFoundException.class, () -> reviewService.createReview(reviewDto));
//    }
//
//    @Test
//    void testUpdateReview_Success() {
//        ReviewDto updateDto = new ReviewDto();
//        Review existingReview = new Review();
//        Review updatedReview = new Review();
//        ReviewDto updatedReviewDto = new ReviewDto();
//
//        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));
//        when(reviewRepository.save(existingReview)).thenReturn(updatedReview);
//        when(reviewMapper.toDto(updatedReview)).thenReturn(updatedReviewDto);
//
//        ReviewDto result = reviewService.updateReview(1L, updateDto);
//
//        assertNotNull(result);
//        verify(reviewRepository).save(existingReview);
//        assertEquals(updatedReviewDto, result);
//    }
//
//    @Test
//    void testUpdateReview_ReviewNotFound() {
//        ReviewDto updateDto = new ReviewDto();
//
//        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ReviewNotFoundException.class, () -> reviewService.updateReview(1L, updateDto));
//    }
//
//    @Test
//    void testDeleteReview_Success() {
//        reviewService.deleteReview(1L);
//
//        verify(reviewRepository).deleteById(1L);
//    }
//
//    @Test
//    void testGetReview_Success() {
//        Review review = new Review();
//        ReviewDto reviewDto = new ReviewDto();
//
//        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
//        when(reviewMapper.toDto(review)).thenReturn(reviewDto);
//
//        ReviewDto result = reviewService.getReview(1L);
//
//        assertNotNull(result);
//        verify(reviewRepository).findById(1L);
//        assertEquals(reviewDto, result);
//    }
//
//    @Test
//    void testGetReview_ReviewNotFound() {
//        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ReviewNotFoundException.class, () -> reviewService.getReview(1L));
//    }
//
//    @Test
//    void testGetReviewsByWorker_Success() {
//        List<Review> reviews = List.of(new Review(), new Review());
//        List<ReviewDto> reviewDtos = List.of(new ReviewDto(), new ReviewDto());
//
//        when(reviewRepository.findByWorkerId(1L)).thenReturn(reviews);
//        when(reviewMapper.toDtoList(reviews)).thenReturn(reviewDtos);
//
//        List<ReviewDto> result = reviewService.getReviewsByWorker(1L);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(reviewRepository).findByWorkerId(1L);
//    }
//}
