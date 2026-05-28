package com.example.JobApplication.Job.Service;

import com.example.JobApplication.Job.Model.Job;
import com.example.JobApplication.Job.Repository.JobRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService{

    // public  List<Job> jobs = new ArrayList<>();
    public final JobRepo jobRepo;

    public JobServiceImpl(JobRepo jobRepo){
     this.jobRepo = jobRepo;
    }


    @Override
    public List<Job> findAll() {
        return jobRepo.findAll();
//        return jobs;
    }

    @Override
    public void createJob(@RequestBody Job postJob) {
        jobRepo.save(postJob);
//        jobs.add(postJob);
    }

    @Override
    public Job findById(Long Id) {
        return jobRepo.findById(Id).orElse(null);

//        for(Job job : jobs){
//            if(job.getId().equals(Id)){
//                return job;
//            }
//        }
//        return null;
    }

    @Override
    public boolean deleteById(Long Id) {
        try {
            jobRepo.deleteById(Id);
            return true;
        }catch(Exception e){
            return false;
        }

//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(Id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public boolean updateJob(Long id, @RequestBody Job values) {
        //here we can use noraml for loop also as we are not removing or adding something
        Optional<Job> optionalJob = jobRepo.findById(id);
//        Iterator<Job> iterator = jobs.iterator();
          if(optionalJob.isPresent()){
              Job job  = optionalJob.get();
                job.setDescription(values.getDescription());
                job.setMaxSalary(values.getMaxSalary());
                job.setMinSalary(values.getMinSalary());
                job.setTitle(values.getTitle());
                job.setLocation(values.getLocation());

              jobRepo.save(job);
                return true;
        }
        return false;
    }
}
