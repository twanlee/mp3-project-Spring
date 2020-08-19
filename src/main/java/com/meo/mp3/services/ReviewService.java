package com.meo.mp3.services;

import com.meo.mp3.models.interactive.Review;

public interface ReviewService extends IService<Review> {
    Review findById(Long id);
    Review createNew();
}
