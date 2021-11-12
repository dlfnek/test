package com.example.test.service;


import com.example.test.domain.Review;
import com.example.test.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Long update(Long id, Review review) {
        Review review1 = reviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("에러가 발생했습니다.")
        );
        review1.update(review);
        return review1.getId();
    }
}
