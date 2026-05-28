package com.example.JobApplication.Reviews;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAllReview(Long companyId);

     Review getById(Long companyId,Long id);

    boolean postReview(Long companyId ,Review review);

    boolean updateReview(Long companyId ,Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);

}
