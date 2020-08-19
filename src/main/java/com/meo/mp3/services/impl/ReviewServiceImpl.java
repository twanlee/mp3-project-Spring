package com.meo.mp3.services.impl;

import com.meo.mp3.models.interactive.Review;
import com.meo.mp3.repositories.ReviewRepository;
import com.meo.mp3.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public Review save(Review model) {
        return null;
    }

    @Override
    public Review delete(Long id) {
        Review review = reviewRepository.findById(id).get();
        reviewRepository.delete(review);
        return review;
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
