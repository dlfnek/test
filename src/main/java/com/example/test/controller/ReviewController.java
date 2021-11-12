package com.example.test.controller;


import com.example.test.domain.Review;
import com.example.test.dto.ReviewRequestDto;
import com.example.test.repository.ReviewRepository;
import com.example.test.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;

    @GetMapping("/api/reviews")
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping("/api/reviews")
    public Review createReview(@RequestBody ReviewRequestDto requestDto) {
        Review review = new Review(requestDto);

        return reviewRepository.save(review);
    }
}
