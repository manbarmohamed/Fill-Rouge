package com.fil.rouge.controller;

import com.fil.rouge.dto.ReviewDto;
import com.fil.rouge.dto.ReviewUpdateDto;
import com.fil.rouge.dto.ReviewWithClientDto;
import com.fil.rouge.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(reviewDto);
        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewWithClientDto> getReview(@PathVariable("id") Long id) {
        ReviewWithClientDto review = reviewService.getReview(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<ReviewWithClientDto>> getReviewsByWorker(@PathVariable("workerId") Long workerId) {
        List<ReviewWithClientDto> reviews = reviewService.getReviewsByWorker(workerId);
        return ResponseEntity.ok(reviews);
    }
}
