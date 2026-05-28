package com.example.JobApplication.Job.Service;


import com.example.JobApplication.Job.Model.Job;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface JobService {
    List<Job> findAll();
    void createJob(@RequestBody Job postJob);
    Job findById(@PathVariable Long Id);

    boolean deleteById(Long id);

    boolean updateJob(@PathVariable  Long id,@RequestBody Job values);
}
