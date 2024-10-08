package com.fil.rouge.service;

import com.fil.rouge.dto.ReviewDto;
import com.fil.rouge.exception.ClientNotFoundException;
import com.fil.rouge.exception.ReviewNotFoundException;
import com.fil.rouge.exception.WorkerNotFoundException;
import com.fil.rouge.mapper.ReviewMapper;
import com.fil.rouge.model.Review;
import com.fil.rouge.model.Client;
import com.fil.rouge.model.Worker;
import com.fil.rouge.repository.ReviewRepository;
import com.fil.rouge.repository.ClientRepository;
import com.fil.rouge.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto createReview(ReviewDto reviewDto) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientRepository.findByUsername(loggedInUser.getName());
        Worker worker = workerRepository.findById(reviewDto.getWorkerId())
                .orElseThrow(() -> new WorkerNotFoundException("Worker not found"));

        Review review = reviewMapper.toEntity(reviewDto);
        review.setClient(client);
        review.setWorker(worker);
        review = reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    public ReviewDto updateReview(Long id, ReviewDto updateDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        reviewMapper.partialUpdate(updateDto, review);
        Review updatedReview = reviewRepository.save(review);
        return reviewMapper.toDto(updatedReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewDto getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
        return reviewMapper.toDto(review);
    }

    public List<ReviewDto> getReviewsByWorker() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        Worker worker = workerRepository.findByUsername(loggedInUser.getName());
        List<Review> reviews = reviewRepository.findByWorkerId(worker.getId());
        return reviewMapper.toDtoList(reviews);
    }
}

