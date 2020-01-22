package com.iispiridis.doctorservice.repositories;

import com.iispiridis.doctorservice.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>
{
    Optional<Doctor> findByuid(int uid);
}
