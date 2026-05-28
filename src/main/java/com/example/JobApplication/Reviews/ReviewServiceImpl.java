package com.example.JobApplication.Reviews;

import com.example.JobApplication.Company.Company;
import com.example.JobApplication.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {


    public final ReviewRepo reviewRepo;
    public final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepo reviewRepo, CompanyService companyService) {
        this.reviewRepo = reviewRepo;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getAllReview(Long companyId) {
//      You'd be asking: "Find me all Reviews that belong to Company 5"
//      select * from reviews where company_id  = companyId
        return reviewRepo.findByCompanyId(companyId);
//   You'd be asking: "Find me the Review with ID = 5"
//        return reviewRepo.findBy(companyId);
    }


    @Override
    public Review getById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }


//    for understanding the flow of extracting and saving in terms of database
//    companyId = 7
//     |
//     ↓
//companyService.getCompanyById(7)  →  returns full Company object (id=7, name="Google"...)
//     |
//     ↓
//review.setCompany(company)        →  Java holds full object in memory
//     |
//     ↓
//reviewRepo.save(review)           →  Hibernate sees @ManyToOne, extracts id=7
//     |
//     ↓
//DB stores → review_id=1, content="Great", rating=5.0, company_id=7

    @Override
    public boolean postReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepo.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
//              Simply links the Review object to the Company object in memory.
            review.setCompany(company); //review has foregin_key company_id it takes set compnay_id(oveerride it if have)
            review.setId(reviewId);
            reviewRepo.save(review);
            return true;
        }
        return false;


//      Company company =companyService.getCompanyById(companyId);
//      Review toupdate =company.getReviews().stream().filter(review1 -> review1.getId().equals(reviewId)).findFirst().orElse(null);
//                if(toupdate != null){
//                    toupdate.setTitle(review.getTitle());
//                    toupdate.setDescription(review.getDescription());
//                    toupdate.setRating(review.getRating());
//                    //toupdate.setCompany(review.getCompany());
//                    reviewRepo.save(toupdate);
//                    return true;
//                }
        //return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        //we are find compnay as review is there in review and company also
        Company company = companyService.getCompanyById(companyId);
        if (reviewRepo.existsById(reviewId)) {
            Review review = reviewRepo.findById(reviewId).orElse(null);
            company.getReviews().remove(review);
            //companyService.updatedata(companyId ,company);
            reviewRepo.deleteById(reviewId);
            return true;
        }
        return false;
    }

}
