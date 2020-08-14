package com.meo.mp3.repositories;

import com.meo.mp3.models.interactive.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
