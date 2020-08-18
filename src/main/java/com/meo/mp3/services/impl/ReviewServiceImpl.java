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
        return reviewRepository.save(review);
    }
}
