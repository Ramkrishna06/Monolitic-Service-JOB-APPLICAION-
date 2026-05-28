package com.example.JobApplication.Reviews;


import com.example.JobApplication.Company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/{companyId}")
public class ReviewController {

    public final ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReview(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReview(companyId);
        if(reviews != null){
            return new ResponseEntity<>(reviews , HttpStatus.OK);
        }
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getById(@PathVariable Long companyId , @PathVariable Long reviewId){
        Review review  = reviewService.getById(companyId,reviewId);
        if(review !=null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/reviews")
    public ResponseEntity<String> postReview(@PathVariable Long companyId ,@RequestBody Review review){
        boolean company = reviewService.postReview(companyId,review);
        if(company) {
            return new ResponseEntity<>("Thanks for you heartfelt feedback", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Company not found add company first" , HttpStatus.NOT_FOUND);
    }


    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId , @PathVariable Long reviewId , @RequestBody Review updatereview){

               boolean isupdated = reviewService.updateReview(companyId,reviewId,updatereview);
               if(isupdated){
                   return new ResponseEntity<>("Your review for company id x is updated" , HttpStatus.OK);
               }
                   return new ResponseEntity<>("Your review id not found or company not exist" , HttpStatus.NOT_FOUND);
    }
//
@DeleteMapping("/reviews/{reviewId}")
public ResponseEntity<String> deleteReview(@PathVariable Long companyId , @PathVariable Long reviewId){

    boolean isdeleted = reviewService.deleteReview(companyId,reviewId);
    if(isdeleted){
        return new ResponseEntity<>("Your review for company id x is updated" , HttpStatus.OK);
    }
    return new ResponseEntity<>("Your review id not found or company not exist" , HttpStatus.NOT_FOUND);
}



}
