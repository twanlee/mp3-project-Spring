package com.meo.mp3.services.impl;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.repositories.ReviewRepository;
import com.meo.mp3.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public Review createNew() {
        Review review = new Review();
        review.setViews(0);
        review.setLikes(0);
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }
}
