package com.meo.mp3.services;

import com.meo.mp3.models.interactive.Review;

public interface IReviewService {
    Review findById(Long id);
    Review createNew();
}
