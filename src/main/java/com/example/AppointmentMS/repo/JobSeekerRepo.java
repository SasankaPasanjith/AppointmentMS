package com.example.AppointmentMS.repo;

import com.example.AppointmentMS.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {
}
