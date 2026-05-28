package com.example.JobApplication;

import com.example.JobApplication.Job.Model.Job;
import com.example.JobApplication.Job.Repository.JobRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class JobApplication {

	public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(JobApplication.class, args);

	}

//    @Bean
//    CommandLineRunner initData(JobRepo jobRepo) {
//        return args -> {
//            // ✅ parameterized constructor used here
//            Job job1 = new Job(null, "Backend Dev", "Spring Boot role", "50000", "100000", "Bangalore");
//            Job job2 = new Job(null, "Frontend Dev", "React role", "40000", "80000", "Mumbai");
//            jobRepo.save(job1);
//            jobRepo.save(job2);
//        };
//    }

}
