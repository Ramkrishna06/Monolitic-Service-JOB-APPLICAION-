package com.example.JobApplication.Job.Controller;


import com.example.JobApplication.Job.Model.Job;
import com.example.JobApplication.Job.Service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs") //so any request comes on localhost8080 is redirected to /jobs
public class JobController {

    private final JobService jobService;


    // it will provide obj of Jobservice ( = new JobServiceImpl() )
    public JobController(JobService jobService){
        this.jobService = jobService;
    }


    @GetMapping
    public  ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobService.findAll();
        if(jobs != null){
            return new ResponseEntity<>(jobs , HttpStatus.OK);
        }
        return new ResponseEntity<>(jobs , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Job> findjobById(@PathVariable Long Id){
        Job job = jobService.findById(Id);
        if(job !=null)
            return new ResponseEntity<>(job , HttpStatus.OK);
        return new ResponseEntity<>(job , HttpStatus.NOT_FOUND);

    }


    @PostMapping
    public ResponseEntity<String> postJob(@RequestBody Job postedJob){
          jobService.createJob(postedJob);
//         return new ResponseEntity("job posted succesfully",HttpStatus.CREATED);

         return ResponseEntity.status(HttpStatus.CREATED).body("job posted succesfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteById(id);
        if(deleted){
            return new ResponseEntity<>("job deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id , @RequestBody Job values){
        boolean updated = jobService.updateJob(id ,values);
        if(updated){
            return new ResponseEntity<>("job updated",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
