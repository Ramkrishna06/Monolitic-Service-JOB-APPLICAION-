package com.example.JobApplication.Job.Repository;

import com.example.JobApplication.Job.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<Job,Long> {


}
