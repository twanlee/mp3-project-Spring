package com.meo.mp3.services;

import com.meo.mp3.models.interactive.Review;

public interface ReviewService {
    Review findById(Long id);
    Review createNew();
    void delete(Review review);
}
