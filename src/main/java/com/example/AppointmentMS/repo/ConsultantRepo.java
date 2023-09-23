package com.example.AppointmentMS.repo;

import com.example.AppointmentMS.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepo extends JpaRepository<Consultant, Integer> {
}
